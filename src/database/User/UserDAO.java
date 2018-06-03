package database.User;

import java.sql.SQLException;

import database.DataAcessObject;

public class UserDAO extends DataAcessObject {
	public static UserDAO InstanseUserDAO;

	protected UserDAO() throws ClassNotFoundException {
		super();
	}

	public static UserDAO createUserDAO() throws ClassNotFoundException {
		InstanseUserDAO = (InstanseUserDAO == null) ? new UserDAO() : InstanseUserDAO;
		return InstanseUserDAO;
	}

	public boolean insertUser(User user) {
		final String SQL = "INSERT INTO User (user_id,user_password,user_firstname,user_secondname,user_phone,user_email) VALUES (?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(SQL);
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstname());
			stmt.setString(4, user.getSecondname());
			stmt.setString(5, user.getPhone());
			stmt.setString(6, user.getEmail());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			System.err.println("=======================================");
			System.err.println("UserDAO.insertUser()");
			System.err.println(e.getMessage());
			System.err.println(e.getSQLState());
			System.err.println("=======================================");
		}
		return false;
	}
}
