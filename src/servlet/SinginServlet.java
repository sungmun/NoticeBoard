package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.User.User;
import database.User.UserDAO;

/**
 * Servlet implementation class SinginServlet
 */
@WebServlet("/Singin")
public class SinginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SinginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String firstname=request.getParameter("firstname");
		String secondname=request.getParameter("secondname");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");

		User user = new User(id, password, firstname, secondname, phone, email);
		UserDAO dao;
		try {
			dao = UserDAO.createUserDAO();

			if (dao.insertUser(user)) {
				response.sendRedirect("./index.jsp");
			} else {
				response.sendRedirect("./JavaServerPage/SingIns.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
