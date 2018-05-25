package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLDataBaseController {
	Connection con;

	public MySQLDataBaseController() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Class load OK");
			con = DriverManager.getConnection(PrivateData.dataBaseUrl, PrivateData.dataBaseId, PrivateData.dataBasePassword);
			System.out.println("DB Connection OK");
		} catch (ClassNotFoundException e) {
			System.err.println("=============================");
			System.err.println("load Error");
			System.err.println("=============================");
		}catch(SQLException e) {
			System.err.println("=============================");
			System.err.println("SQL Error");
			System.err.println("=============================");
		}
	}

	public Connection getConnection() {
		return con;
	}

}
