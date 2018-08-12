package Exception;

import java.io.PrintWriter;

import com.google.gson.JsonObject;

public class JsProcessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4516850744701207841L;
	PrintWriter write;
	String message;
	public JsProcessException(PrintWriter write,String message) {
		this.write=write;
		this.message=message;
	}

	public void printStackTrace() {
		JsonObject json = new JsonObject();
		json.addProperty("err", message);
		write.println(json);
	}
}
