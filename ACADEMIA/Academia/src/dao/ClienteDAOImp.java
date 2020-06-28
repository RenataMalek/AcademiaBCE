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
       
    	
    	PreparedStatement preparedStatement = connection.prepareStatement("select * from cliente where cpf_cliente = ?");
        
    	preparedStatement.setString(1, cpf);

        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            Cliente cliente = new Cliente(resultSet.getLong("id_cliente"), resultSet.getString("cpf_cliente"),
                    resultSet.getString("nome_cliente"), resultSet.getString("email_cliente"), resultSet.getString("telefone_cliente"),
                    resultSet.getString("endereco_cliente"));
            return cliente;
        } else {
            return null;
        }
    }
}
