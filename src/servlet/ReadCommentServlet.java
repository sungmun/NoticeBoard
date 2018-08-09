package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post = Integer.parseInt(request.getParameter("post"));
		List<Comment> list = null;
		try {
			CommentDAO dao = CommentDAO.createCommentDAO();
			list = dao.getCommentList(post);
			
		} catch (ClassNotFoundException e) {
			System.err.println("error");
		}

		response.setCharacterEncoding("UTF-8");
		PrintWriter write= response.getWriter();
		
		Comment[] arraylist=list.toArray(new Comment[list.size()]);
		
		JsonObject json=new JsonObject();
		json.addProperty("list", new Gson().toJson(list));
		
		write.println(json);
	}
	

}
