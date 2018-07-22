package database.Comment;

import java.sql.Date;
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
		String SQL = "SELECT notice_num,member_id,comment_day FROM Comment WHERE notice_num=? order by comment_day";
		
		ArrayList<Comment> array = new ArrayList<>();
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, post);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Comment comment=new Comment();
				comment.setCommentContents(rs.getString("comment_contents"));
				comment.setMemberId(rs.getString("member_id"));
				comment.setCommentDay(rs.getDate("comment_day").toString());
				array.add(comment);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return array;
	}
	public boolean insertComment(final Comment comment) {
		String SQL = "INSERT INTO Comment(notice_num,member_id,comment_day,comment_contents) VALUES(?,?,?,?)";
		
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, comment.getNoticeNum());
			stmt.setString(2, comment.getMemberId());
			stmt.setDate(3, Date.valueOf(comment.getCommentDay()));
			stmt.setString(4, comment.getCommentContents());

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
