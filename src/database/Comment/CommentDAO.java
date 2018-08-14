package database.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Exception.SQLCustomException;
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
				list.add(getComment(rs));
			}
		} catch (SQLException e) {
			throw new SQLCustomException(SQL, e);
		}
		return list;
	}

	public Comment getComment(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		comment.setCommentNum(rs.getInt("comment_num"));
		comment.setNoticeNum(rs.getInt("notice_num"));
		comment.setReCommentGroup(rs.getInt("re_comment_group"));
		comment.setCommentContents(rs.getString("comment_contents"));
		comment.setMemberId(rs.getString("member_id"));
		comment.setCommentDay(rs.getDate("comment_day").toString());
		return comment;
	}

	public Comment getComment(int commentNum) throws SQLException {
		String SQL = "SELECT * FROM Comment WHERE comment_num=?";
		stmt = con.prepareStatement(SQL);
		stmt.setInt(1, commentNum);

		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			Comment comment = new Comment();
			comment.setCommentNum(rs.getInt("comment_num"));
			comment.setNoticeNum(rs.getInt("notice_num"));
			comment.setReCommentGroup(rs.getInt("re_comment_group"));
			comment.setCommentContents(rs.getString("comment_contents"));
			comment.setMemberId(rs.getString("member_id"));
			comment.setCommentDay(rs.getDate("comment_day").toString());
			return comment;
		}
		return null;
	}

	public boolean insertComment(Comment comment) {
		boolean group = comment.getReCommentGroup() != 0;
		String SQL = "INSERT INTO Comment(notice_num,member_id,comment_contents";
		SQL += group ? ",re_comment_group" : "";
		SQL += ") VALUES(?,?,?";
		SQL += (group ? ",?" : "") + ")";

		try {
			stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, comment.getNoticeNum());
			stmt.setString(2, comment.getMemberId());
			stmt.setString(3, comment.getCommentContents());
			
			if (group) {
				stmt.setInt(4, comment.getReCommentGroup());
			}
			
			stmt.executeUpdate();

			if (group) {
				return true;
			}
			
			ResultSet rs = stmt.getGeneratedKeys();

			if (!rs.next()) {
				return false;
			}
			return updateCommentGroup(rs.getInt(1),rs.getInt(1));
		} catch (SQLException e) {
			throw new SQLCustomException(SQL, e);
		}
	}

	public boolean updateCommentGroup(final int reCommentGroupNum,final int commentNum) {
		String SQL = "UPDATE Comment SET re_comment_group=? WHERE comment_num=?";

		try {
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, reCommentGroupNum);
			stmt.setInt(2, commentNum);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new SQLCustomException(SQL, e);
		}
	}
}
