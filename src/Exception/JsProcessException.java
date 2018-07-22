package Exception;
import java.io.PrintWriter;

public class JsProcessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4516850744701207841L;
	PrintWriter write;
	public JsProcessException(String message) {
		super(message);
	}

}
