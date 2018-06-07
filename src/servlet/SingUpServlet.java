package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.User.User;
import database.User.UserDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/SingUp")
public class SingUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SingUpServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO dao = null;
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		if(!pageload(id, password, request, response)) {
			return;
		}
		try {
			dao = UserDAO.createUserDAO();
			User user = dao.selectUser(id, password);
			session.setAttribute("login", user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean pageload(String id, String password,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (!(id == null || password == null)) {
			return true;
		}
		RequestDispatcher rd=request.getRequestDispatcher("/JavaServerPage/SingUps.jsp");
		rd.forward(request, response);
		return false;
	}
}
