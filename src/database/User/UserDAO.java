package database.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import Exception.SQLCustomException;
import database.DataAcessObject;

public class UserDAO extends DataAcessObject {
	private static UserDAO InstanseUserDAO;

	protected UserDAO() throws ClassNotFoundException {
		super();
	}

	public static UserDAO createUserDAO() throws ClassNotFoundException {
		InstanseUserDAO = (InstanseUserDAO == null) ? new UserDAO() : InstanseUserDAO;
		return InstanseUserDAO;
	}

	public User searchUser(String id) {
		final String SQL = "Select * from User where user_id = ?";
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			User user = new User();
			user.setId(rs.getString("user_id"));
			user.setFirstname(rs.getString("user_firstname"));
			user.setSecondname(rs.getString("user_secondname"));
			user.setImage(rs.getString("user_image"));
			user.setJoindate(rs.getDate("joindate"));
			return user;
		} catch (SQLException e) {
			throw new SQLCustomException("UserDAO.selectUser(" + id + ")",e);
		}	
	}

	public User selectUser(String id, String password) {
		final String SQL = "Select * from User where user_id = ? AND user_password = ?";
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setString(1, id);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			User user = new User();
			user.setId(rs.getString("user_id"));
			user.setPassword(rs.getString("user_password"));
			user.setFirstname(rs.getString("user_firstname"));
			user.setSecondname(rs.getString("user_secondname"));
			user.setPhone(rs.getString("user_phone"));
			user.setEmail(rs.getString("user_email"));
			user.setImage(rs.getString("user_image"));
			user.setJoindate(rs.getDate("joindate"));
			return user;
		} catch (SQLException e) {
			throw new SQLCustomException( "UserDAO.selectUser(" + id + "," + password + ")",e);
		}
	}

	public boolean updateUser(User user) {
		String SQL = "UPDATE User SET user_password = ?, user_phone = ? , user_email = ?) where user_id = ? ";

		try {
			stmt = con.prepareStatement(SQL);
			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getPhone());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getId());
			stmt.execute();

			return true;
		} catch (SQLException e) {
			throw new SQLCustomException("UserDAO.insertUser(" + user + ")",e);
		}
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
			throw new SQLCustomException("UserDAO.insertUser(" + user + ")",e);
		}
	}


	public boolean isIdDuplicateCheck(String id) {
		final String SQL = "Select * from User where user_id = ?";
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			throw new SQLCustomException("UserDAO.isIdDuplicateCheck(" + id + ")",e);
		}
		return false;
	}
}
