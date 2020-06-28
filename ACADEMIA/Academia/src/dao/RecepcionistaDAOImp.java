package dao;

import academia.Cliente;
import academia.Contrato;
import academia.Recepcionista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecepcionistaDAOImp implements RecepcionistaDAO {

	private Connection connection;

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

		
		
	}

	@Override
	public Contrato pesquisarPorCPFCon(String CPF) {
		// TODO Auto-generated method stub
		return null;
	}
}
