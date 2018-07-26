package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.Notice.Notice;
import database.Notice.NoticeDAO;

/**
 * Servlet implementation class ListLoadServlet
 */
@WebServlet({ "/ListLoad", "/index" })
public class ListLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListLoadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNum = request.getParameter("page");
		if (request.getParameter("search") != null) {
			searchListLoad(request, response);
			return;
		}
		if (pageNum == null) {
			firstListLoad(request, response);
			return;
		}
		NoticeDAO DAO;
		try {
			DAO = NoticeDAO.createNoticeDAO();

			final int noticeCount;
			ArrayList<Notice> list = DAO.getNoticeList(1);
			noticeCount = DAO.getNoticCount();
			int MAX_PAGE = noticeCount / 20 + (noticeCount % 20 > 0 ? 1 : 0);

			response.getWriter().println(new Gson().toJson(list.toArray(new Notice[list.size()])));
			response.getWriter().println(new Gson().toJson(MAX_PAGE));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchListLoad(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String search = request.getParameter("search");
		int pageNum = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));
		NoticeDAO DAO;
		try {
			DAO = NoticeDAO.createNoticeDAO();
			ArrayList<Notice> list = DAO.getNoticeList("SELECT * FROM Notice WHERE notice_title Like \"%" + search
					+ "%\" ORDER BY notice_num desc LIMIT ?, 20", pageNum);
			final int noticeCount = DAO.getNoticCount();
			int MAX_PAGE = noticeCount / 20 + (noticeCount % 20 > 0 ? 1 : 0);
			response.getWriter().println(new Gson().toJson(list.toArray(new Notice[list.size()])));
			response.getWriter().println(new Gson().toJson(MAX_PAGE));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void firstListLoad(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDAO DAO = null;
		try {
			DAO = NoticeDAO.createNoticeDAO();

			final int noticeCount;
			ArrayList<Notice> list = DAO.getNoticeList(1);

			noticeCount = DAO.getNoticCount();

			int MAX_PAGE = noticeCount / 20 + (noticeCount % 20 > 0 ? 1 : 0);
			request.setAttribute("noticeList", list);
			request.setAttribute("maxPage", MAX_PAGE);

			RequestDispatcher dispatcher = request.getRequestDispatcher("./index.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
		}
	}
}
