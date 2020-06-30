package dao;

import academia.Cliente;
import academia.Cobranca;
import academia.Contrato;
import academia.Modalidade;
import academia.Recepcionista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecepcionistaDAOImp implements RecepcionistaDAO {

	private Connection connection;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RecepcionistaDAOImp() {
		try {
			this.connection = new ConexaoDAO().getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Recepcionista buscarPorCodigoAcesso(String codigoAcesso) throws SQLException {
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from recepcionista where codigoAcesso = ?");
		preparedStatement.setString(1, codigoAcesso);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			Recepcionista recepcionista = new Recepcionista(resultSet.getLong("id_recepcionista"),
					resultSet.getString("nome"), resultSet.getString("cpf"), resultSet.getString("telefone"),
					resultSet.getString("codigoAcesso"));
			return recepcionista;
		} else {
			return null;
		}
	}

	@Override
	public void adicionarCliente(Cliente c) {

		try {

			String sql = "INSERT INTO cliente (ID_CLIENTE, CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, TELEFONE_CLIENTE, ENDERECO_CLIENTE)"
					+ "VALUES (0, ?, ?, ?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, c.getCPF());
			stm.setString(2, c.getNome());
			stm.setString(3, c.getEmail());
			stm.setString(4, c.getTelefone());
			stm.setString(5, c.getEndereco());

			stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Cliente pesquisarPorCPF(String CPF) {

		try {
			String sql = "SELECT * FROM cliente WHERE CPF_CLIENTE like ?";

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, "%" + CPF + "%");
			ResultSet rs = stm.executeQuery();
			Cliente c = new Cliente();

			if (rs.next()) {
				c.setID(rs.getLong("ID_CLIENTE"));
				c.setCPF(rs.getString("CPF_CLIENTE"));
				c.setNome(rs.getString("NOME_CLIENTE"));
				c.setEmail(rs.getString("EMAIL_CLIENTE"));
				c.setTelefone(rs.getString("TELEFONE_CLIENTE"));
				c.setEndereco(rs.getString("ENDERECO_CLIENTE"));

				return c;
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void adicionarContrato(Contrato c) {

		try {

			String sql = "INSERT INTO contrato (ID_CONTRATO, CPF_CLIENTE_FK, DATA_CONTRATO, VALOR_MENSAL_CONTRATO, PARCELAS, QTD_TREINOS)"
					+ "VALUES (0, ?, ?, ?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, c.getCpf_cli());
			stm.setDate(2, java.sql.Date.valueOf(c.getDataContrato()));
			stm.setDouble(3, c.getValorMes());
			stm.setInt(4, c.getQtdParcelas());
			stm.setInt(5, c.getQtdTreinos());

			stm.executeUpdate();

			gerarCobranca(c);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void gerarCobranca(Contrato c) {

		Contrato pegarID = pesquisarPorCPFCon(c.getCpf_cli());

		int aux = c.getQtdParcelas();
		LocalDate ld = c.getDataContrato();
		LocalDate proximo;
		int cont = 1;

		int dia = ld.getDayOfMonth();
		int mes = ld.getMonthValue();
		int ano = ld.getYear();

		String m = "/";

		if (mes != 10 && mes != 11 && mes != 12) {
			m = "/0";
		}

		String data = dia + m + mes + "/" + ano;

		for (int i = 0; i < aux; i++) {

			try {

				proximo = LocalDate.parse(data, dtf);

				String sql = "INSERT INTO cobranca (ID_COBRANCA, ID_CONTRATO_FK, DIA_VCTO_COBRANCA, VALOR_COBRANCA, NUM_PARCELA_COBRANCA, PAGO_COBRANCA)"
						+ "VALUES (0, ?, ?, ?, ?, 1)";
				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setLong(1, pegarID.getID());
				stm.setDate(2, java.sql.Date.valueOf(proximo));
				stm.setDouble(3, c.getValorMes());
				stm.setInt(4, cont);

				stm.executeUpdate();
				cont++;

				if (mes < 12) {
					mes++;
				} else {
					mes = 0;
				}

				if (mes == 0) {
					ano++;
				} else {

				}

				if (dia >= 29 && mes == 2) {
					if ((ano % 4 == 0) && (ano % 100 != 0) || (ano % 400 == 0)) {
						dia = 29;
					}
				} else {
					if (mes == 2) {
						dia = 28;

					}
				}

				if (mes != 10 && mes != 11 && mes != 12) {
					m = "/0";
				}

				data = dia + m + mes + "/" + ano;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public List<Cobranca> tableViewCobranca(long idContrato) {

		List<Cobranca> lista = new ArrayList<Cobranca>();

		try {

			String sql = "SELECT * FROM cobranca WHERE ID_CONTRATO_FK like ?";
			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, "%" + idContrato + "%");
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Cobranca c = new Cobranca();
				c.setID_Cobranca((rs.getLong("ID_COBRANCA")));
				c.setID_Contrato(rs.getLong("ID_CONTRATO_FK"));
				c.setDataPgto(rs.getDate("DIA_VCTO_COBRANCA").toLocalDate());
				c.setValor(rs.getDouble("VALOR_COBRANCA"));
				c.setNumParcela(rs.getInt("NUM_PARCELA_COBRANCA"));
				c.setPago(rs.getBoolean("PAGO_COBRANCA"));
				lista.add(c);
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	@Override
	public Contrato pesquisarPorCPFCon(String CPF) {

		try {
			String sql = "SELECT * FROM contrato WHERE CPF_CLIENTE_FK like ?";

			PreparedStatement stm = connection.prepareStatement(sql);

			stm.setString(1, "%" + CPF + "%");
			ResultSet rs = stm.executeQuery();
			Contrato c = new Contrato();

			if (rs.next()) {

				c.setID(rs.getLong("ID_CONTRATO"));
				c.setCpf_cli(rs.getString("CPF_CLIENTE_FK"));
				c.setDataContrato(rs.getDate("DATA_CONTRATO").toLocalDate());
				c.setValorMes(rs.getDouble("VALOR_MENSAL_CONTRATO"));
				c.setQtdParcelas(rs.getInt("PARCELAS"));
				c.setQtdTreinos(rs.getInt("QTD_TREINOS"));

				c.setValorTotal(c.getValorMes() * c.getQtdParcelas());

				return c;
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
