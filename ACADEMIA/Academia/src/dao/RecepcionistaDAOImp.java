package dao;

import academia.Cliente;
import academia.Contrato;
import academia.Recepcionista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

		int aux = c.getQtdParcelas();
		LocalDate ld;
		LocalDate proximo = c.getDataContrato();
		int cont = 1;

		for (int i = 0; i < aux; i++) {

			try {
				
				ld = proximo;
				
				int dia = ld.getDayOfMonth();
				int mes = ld.getMonthValue();
				int ano = ld.getYear();
				
				String m = "/";
				
				if (mes != 10 && mes != 11 && mes != 12) {
					m = "/0";
				}

				String data = dia + m + mes + "/" + ano;

				proximo = LocalDate.parse(data, dtf);

				String sql = "INSERT INTO cobranca (ID_COBRANCA, ID_CONTRATO_FK, DIA_VCTO_COBRANCA, VALOR_COBRANCA, NUM_PARCELA_COBRANCA, PAGO_COBRANCA)"
						+ "VALUES (0, ?, ?, ?, ?, 1)";
				PreparedStatement stm = connection.prepareStatement(sql);
				stm.setLong(1, c.getID());
				stm.setDate(2, java.sql.Date.valueOf(proximo));
				stm.setDouble(3, c.getValorMes());
				stm.setInt(4, cont );

				stm.executeUpdate();
				
				cont++;
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

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
