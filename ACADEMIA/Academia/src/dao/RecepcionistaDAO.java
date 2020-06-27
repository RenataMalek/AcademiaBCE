package dao;

import academia.Recepcionista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecepcionistaDAO {

    private Connection connection;

    public RecepcionistaDAO() {
        try {
            this.connection = new ConexaoDAO().getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Recepcionista buscarPorCodigoAcesso(String codigoAcesso) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from recepcionista where codigoAcesso = ?");
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
}
