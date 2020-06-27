package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

	private Connection connection;

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		/*
		 * String hostName = "localhost"; String dbName = "academia"; String user =
		 * "root"; String senha = "123456"; Class.forName("com.mysql.cj.jdbc.Driver");
		 */

		final String URL = "jdbc:mysql://localhost:3306/academia?useTimezone=true&serverTimezone=UTC";
		final String USER = "root";
		final String PASS = "123456";
		
		Class.forName("com.mysql.cj.jdbc.Driver");

		connection = DriverManager.getConnection(URL, USER, PASS);

		return connection;
	}
}
