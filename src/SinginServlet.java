

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.User.UserDAO;
import database.User.User;

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
        // TODO Auto-generated constructor stub
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
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setFirstname(request.getParameter("firstname"));
		user.setSecondname(request.getParameter("secondname"));
		user.setId(request.getParameter("id"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(request.getParameter("phone"));
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
