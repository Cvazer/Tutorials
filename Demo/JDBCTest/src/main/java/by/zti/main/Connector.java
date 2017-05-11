package by.zti.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connector {
	public static Connection connection;
	public static boolean connected;

	public static void connect(String URL, String user, String pass) throws SQLException {
		Properties prop = new Properties();
		prop.setProperty("useSSL", "true");
		prop.setProperty("user", user);
		prop.setProperty("password", pass);
		connection = DriverManager.getConnection(URL, prop);
		connected = !connection.isClosed();
	}
	
	public static ResultSet executeQuary(String SQL) throws SQLException{
		Statement stm = connection.createStatement();
		return stm.executeQuery(SQL);
	}
	
	public static void disconnect() throws SQLException{
		connection.close();
		connected = !connection.isClosed();
	}

	public static void executeUpdate(String quary) throws SQLException {
		Statement stm = connection.createStatement();
		stm.executeUpdate(quary);
	}

}
