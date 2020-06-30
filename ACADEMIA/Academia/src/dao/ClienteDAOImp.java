package dao;

import academia.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImp {

    private Connection connection;

    public ClienteDAOImp() {
        try {
            this.connection = new ConexaoDAO().getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Cliente buscarClientePorCpf(String cpf) throws SQLException {

    	PreparedStatement preparedStatement = connection.prepareStatement("select * from cliente where cpf_cliente like ?");
        
    	preparedStatement.setString(1, cpf);

    	
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            Cliente cliente = new Cliente(resultSet.getLong("ID_CLIENTE"), resultSet.getString("CPF_CLIENTE"),
                    resultSet.getString("NOME_CLIENTE"), resultSet.getString("EMAIL_CLIENTE"), resultSet.getString("TELEFONE_CLIENTE"),
                    resultSet.getString("ENDERECO_CLIENTE"));
            return cliente;
        } else {
            return null;
        }
    }
}
