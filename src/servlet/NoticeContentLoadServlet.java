package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Notice.Notice;
import database.Notice.NoticeDAO;

/**
 * Servlet implementation class NoticeContentLoadServlet
 */
@WebServlet("/NoticeContent")
public class NoticeContentLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeContentLoadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		
		NoticeDAO DAO;
		try {
			DAO = NoticeDAO.createNoticeDAO();
			Notice notice = DAO.getNotice(Integer.parseInt(id));
			request.setAttribute("notice", notice);

			RequestDispatcher dispatcher = request.getRequestDispatcher("JavaServerPage/NoticeContent.jsp");
			
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
