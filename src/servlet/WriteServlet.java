package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Notice.Notice;
import database.Notice.NoticeDAO;
import database.User.User;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/Write")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session=request.getSession();
			
			NoticeDAO dao=NoticeDAO.createNoticeDAO();
			Notice notice=new Notice();
			
			notice.setNotice_title(request.getParameter("title"));
			notice.setNotice_contents(request.getParameter("content"));
			notice.setMember_id(((User)session.getAttribute("login")).getId());
			
			dao.insertNotice(notice);
			
			response.sendRedirect("./index.jsp");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
