package database.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataAcessObject;

public class CommentDAO extends DataAcessObject {
	private static CommentDAO InstanseCommentDAO;

	protected CommentDAO() throws ClassNotFoundException {
		super();
	}

	public static CommentDAO createCommentDAO() throws ClassNotFoundException {
		InstanseCommentDAO = (InstanseCommentDAO == null) ? new CommentDAO() : InstanseCommentDAO;
		return InstanseCommentDAO;
	}

	public ArrayList<Comment> getCommentList(final int post) {
		String SQL = "SELECT * FROM Comment WHERE notice_num=?";
		
		ArrayList<Comment> array = new ArrayList<>();
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, post);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Comment comment=new Comment();
				comment.setCommentContents(rs.getString("comment_contents"));
				comment.setMemberId(rs.getString("member_id"));
				array.add(comment);
			}
		} catch (SQLException e) {
		}
		return array;
	}
}
