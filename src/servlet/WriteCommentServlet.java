package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exception.JsProcessException;
import database.Comment.Comment;
import database.Comment.CommentDAO;
import database.User.User;

/**
 * Servlet implementation class WriteCommentServlet
 */
@WebServlet("/WriteComment")
public class WriteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WriteCommentServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post = Integer.parseInt(request.getParameter("post"));
		String commentContents = request.getParameter("contents");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("login");

		response.setCharacterEncoding("UTF-8");
		PrintWriter write =response.getWriter();
		try {
			if (commentContents == "")
				throw new JsProcessException(write,"댓글이 없습니다. 댓글을 달아 주세요");
			else if (user == null)
				throw new JsProcessException(write,"로그인을 하고 댓글을 달아 주세요");
			Comment comment = new Comment();
			comment.setCommentContents(commentContents);
			comment.setMemberId(user.getId());
			comment.setNoticeNum(post);

			CommentDAO dao;
			try {
				dao = CommentDAO.createCommentDAO();
				dao.insertComment(comment);
			} catch (ClassNotFoundException e) {
			}
		} catch (JsProcessException e) {
			e.printStackTrace();
		}
	}

}
