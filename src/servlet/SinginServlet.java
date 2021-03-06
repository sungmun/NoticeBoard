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
		User user=new User();
		user.setId(request.getParameter("id"));
		user.setPassword(request.getParameter("password1"));
		user.setFirstname(request.getParameter("firstname"));
		user.setSecondname(request.getParameter("secondname"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		UserDAO dao;
		try {
			dao = UserDAO.createUserDAO();

			if (dao.insertUser(user)) {
				response.sendRedirect("index");
			} else {
				response.sendRedirect("JavaServerPage/SingIn/SingInPage.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
