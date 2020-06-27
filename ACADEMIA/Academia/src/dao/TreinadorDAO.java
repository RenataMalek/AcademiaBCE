package dao;

import academia.Treinador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TreinadorDAO {

    private Connection connection;

    public TreinadorDAO() {
        try {
            this.connection = new ConexaoDAO().getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Treinador buscarPorCodigoAcesso(String codigoAcesso) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from treinador where codigoAcesso = ?");
        preparedStatement.setString(1, codigoAcesso);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Treinador treinador = new Treinador(resultSet.getLong("id_treinador"),
                    resultSet.getString("nome"), resultSet.getString("cpf"), resultSet.getString("telefone"),
                    resultSet.getString("codigoAcesso"));
            return treinador;
        } else {
            return null;
        }
    }
}
