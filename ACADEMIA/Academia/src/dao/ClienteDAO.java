package dao;

import academia.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() {
        try {
            this.connection = new ConexaoDAO().getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Cliente buscarClientePorCpf(String cpf) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from cliente where cpf = ?");
        preparedStatement.setString(1, cpf);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Cliente cliente = new Cliente(resultSet.getLong("id_cliente"), resultSet.getString("cpf"),
                    resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("telefone"),
                    resultSet.getString("endereco"));
            return cliente;
        } else {
            return null;
        }
    }
}
