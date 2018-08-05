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
import com.google.gson.JsonObject;

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
	public boolean isNull(String str) {
		return (str == null || str == "") ? false : true;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("page");

		if (isNull(request.getParameter("search"))) {
			searchListLoad(request, response);
			return;
		}
		if (!isNull(pageNum)) {
			firstListLoad(request, response);
			return;
		}
		NoticeDAO DAO;
		try {
			DAO = NoticeDAO.createNoticeDAO();

			ArrayList<Notice> list = DAO.getNoticeList(Integer.parseInt(pageNum));
			
			JsonObject json=new JsonObject();
			json.addProperty("list", new Gson().toJson(list.toArray(new Notice[list.size()])));
			response.getWriter().println(json.toString());
			
		} catch (ClassNotFoundException e) {
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
			final int noticeCount = DAO.getNoticCount("SELECT COUNT(*) as cnt FROM Notice WHERE notice_title Like \"%" + search
					+ "%\"");
			int MAX_PAGE = noticeCount / 20 + (noticeCount % 20 > 0 ? 1 : 0);
			JsonObject json=new JsonObject();
			json.addProperty("list", new Gson().toJson(list.toArray(new Notice[list.size()])));
			json.addProperty("maxPage", MAX_PAGE);
//			Gson json=new Gson();
//			json.fromJson("list", json.)
			
			response.getWriter().println(json.toString());
			
//			response.getWriter().println(new Gson().toJson(MAX_PAGE));
		} catch (ClassNotFoundException e) {
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
