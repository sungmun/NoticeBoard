package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DataAcessObject {
	
	protected Connection con;
	protected PreparedStatement stmt;
	
	protected DataAcessObject() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			con = DriverManager.getConnection(PrivateData.dataBaseUrl, PrivateData.dataBaseId, PrivateData.dataBasePassword);
		} catch(SQLException e) {
			System.err.println("=============================");
			System.err.println("Connection Error");
			System.err.println("=============================");
		}
	}

}
