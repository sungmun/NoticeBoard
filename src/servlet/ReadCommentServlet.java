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
 * Servlet implementation class ReadCommentServlet
 */
@WebServlet("/ReadComment")
public class ReadCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post = Integer.parseInt(request.getParameter("post"));
		ArrayList<Comment> list = null;
		try {
			CommentDAO dao = CommentDAO.createCommentDAO();
			list = dao.getCommentList(post);
		} catch (ClassNotFoundException e) {
			System.err.println("error");
		}

		response.setCharacterEncoding("UTF-8");
		PrintWriter write= response.getWriter();
		
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
			write.append(comment.getCommentDay());
			write.append("</div>");
			write.append("</div>");
			write.append("<br>");
			write.append("<section class=\"panel-body panel-default\">");
			write.append(comment.getCommentContents());
			write.append("</section>");
		}
	}
	

}