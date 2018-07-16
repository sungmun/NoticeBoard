package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Comment.Comment;
import database.Comment.CommentDAO;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int post = Integer.parseInt(request.getParameter("post"));
		String commentContents = request.getParameter("contents");
		String memberId = request.getParameter("memberid");
		response.setCharacterEncoding("UTF-8");
		PrintWriter write = response.getWriter();

		if(commentContents==null) {
			readComment(write, post);
			return;
		}
		
		if (commentContents == "") {
			errorMessageJs(write, "댓글이 없습니다. 댓글을 입력해 주세요");
			return;
		} else if (memberId == null) {
			errorMessageJs(write, "로그인을 하고 댓글을 달아 주세요");
			return;
		}
		
		readComment(write, post);
	}

	public void errorMessageJs(PrintWriter write, String message) {
		write.append("<script type=\"text/javascript\">");
		write.append("alert(\"");
		write.append(message);
		write.append("\");");
		write.append("</script>");
	}

	public void readComment(PrintWriter write, int post) throws IOException {
		ArrayList<Comment> list = null;
		try {
			CommentDAO dao = CommentDAO.createCommentDAO();
			list = dao.getCommentList(post);
		} catch (ClassNotFoundException e) {
		}

		if (list == null) {
			return;
		}
		for (Comment comment : list) {
			write.append("<div class=\"post-meta\">");
			write.append("<div class=\"pull-left\">");
			write.append("<a href=\"#\">");
			write.append(comment.getMemberId());
			write.append("</a>");
			write.append("</div>");
			write.append("<div class=\"pull-right\">");
			write.append("2017-01-01");
			write.append("</div>");
			write.append("</div>");
			write.append("<br>");
			write.append("<section class=\"panel-body panel-default\">");
			write.append(comment.getCommentContents());
			write.append("</section>");
		}
	}
}
