package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import database.User.UserDAO;

/**
 * Servlet implementation class CheackServlet
 */
@WebServlet("/Check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			checkId(request, response, id);
			return;
		}
	}

	private void checkId(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		UserDAO dao = null;
		try {
			dao = UserDAO.createUserDAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		JsonObject json=new JsonObject();
		json.addProperty("return", dao.isIdDuplicateCheck(id));
		response.getWriter().println(json);
	}

}
