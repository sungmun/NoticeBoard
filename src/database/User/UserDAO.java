package database.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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

	public User selectUser(String id, String password) {
		final String SQL = "Select * from User where user_id = ? AND user_password = ?";
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setString(1, id);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			String userId = rs.getString("user_id");
			String userPassword = rs.getString("user_password");
			String userFirstname = rs.getString("user_firstname");
			String userSecondname = rs.getString("user_secondname");
			String userPhone = rs.getString("user_phone");
			String userEmail = rs.getString("user_email");
			String userImage = rs.getString("user_image");
			Date userJoindate = rs.getDate("joindate");
			return new User(userId, userPassword, userFirstname, userSecondname, userPhone, userEmail, userImage,
					userJoindate);
		} catch (SQLException e) {
			errMessageprint(e, "UserDAO.selectUser(" + id + "," + password + ")");
		}
		return null;
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
			errMessageprint(e, "UserDAO.insertUser("+user+")");
		}
		return false;
	}

	public void errMessageprint(Exception e, String callMethod) {
		System.err.println("=======================================");
		System.err.println(callMethod);
		System.err.println(e.getMessage());
		if (e instanceof SQLException) {
			System.err.println(((SQLException) e).getSQLState());
		}
		System.err.println("=======================================");
	}

	public boolean isIdDuplicateCheck(String id) {
		final String SQL = "Select * from User where user_id = ?";
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())	return true;
		}catch (SQLException e) {
			errMessageprint(e, "UserDAO.isIdDuplicateCheck("+id+")");
		}
		return false;
	}
}
