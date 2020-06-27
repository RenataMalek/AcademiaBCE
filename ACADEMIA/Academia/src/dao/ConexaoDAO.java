package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    private Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        String hostName = "localhost";
        String dbName = "academia";
        String user = "root";
        String senha = "";
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://" + hostName + ":3306/" + dbName, user, senha);

        return connection;
    }
}
