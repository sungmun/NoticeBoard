package Exception;

import java.sql.SQLException;

public class SQLCustomException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6498964170230139972L;

	public SQLCustomException(String message,SQLException e) {
		System.err.println("=======================================");
		System.err.println(message);
		System.err.println(e.getSQLState());
		System.err.println("=======================================");
	}
}
