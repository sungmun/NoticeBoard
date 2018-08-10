package database.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

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

	public LinkedList<Comment> getCommentList(final int post) {
		String SQL = "SELECT * FROM Comment WHERE notice_num=? order by re_comment_group,comment_day";
		
		LinkedList<Comment> list = new LinkedList<>();
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, post);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Comment comment=new Comment();
				comment.setCommentNum(rs.getInt("comment_num"));
				comment.setReCommentGroup(rs.getInt("re_comment_group"));
				comment.setCommentContents(rs.getString("comment_contents"));
				comment.setMemberId(rs.getString("member_id"));
				comment.setCommentDay(rs.getDate("comment_day").toString());
				list.add(comment);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return list;
	}
	public boolean insertComment(final Comment comment) {
		String SQL = "INSERT INTO Comment(notice_num,member_id,comment_contents) VALUES(?,?,?)";
		
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, comment.getNoticeNum());
			stmt.setString(2, comment.getMemberId());
			stmt.setString(3, comment.getCommentContents());

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
}
