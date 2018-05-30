package database.Notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataAcessObject;

public class NoticeDAO extends DataAcessObject {
	public static NoticeDAO InstanseNoticDAO;

	protected NoticeDAO() throws ClassNotFoundException {
		super();
	}

	public static NoticeDAO createNoticeDAO() throws ClassNotFoundException {
		InstanseNoticDAO = (InstanseNoticDAO == null) ? new NoticeDAO() : InstanseNoticDAO;
		return InstanseNoticDAO;
	}

	public ArrayList<Notice> getNoticeList() {
		return getNoticeList(1);
	}

	public ArrayList<Notice> getNoticeList(int page) {
		final int limit = 20;
		final String SQL = "SELECT * FROM Notice LIMIT ?, ?";

		ArrayList<Notice> array = new ArrayList<>();
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, limit * (page - 1));
			stmt.setInt(2, limit * page);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setMember_id(rs.getString("Member_id"));
				notice.setNotice_title(rs.getString("notice_title"));
				notice.setNotice_date(rs.getDate("notice_date"));
				notice.setNotice_num(rs.getInt("notice_count"));
				notice.setNotice_count(rs.getInt("notice_num"));
				array.add(notice);
			}
		} catch (SQLException e) {
			System.err.println("=======================================");
			System.err.println("NoticeDAO.getNoticeList()");
			System.err.println(SQL);
			System.err.println("=======================================");
		}

		return array;
	}

	public int getNoticCount() {
		final String SQL = "SELECT COUNT(*) as cnt FROM Notice";
		try {
			stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cnt");
			}
		} catch (SQLException e) {
			System.err.println("=======================================");
			System.err.println("NoticeDAO.getNoticCount()");
			System.err.println(SQL);
			System.err.println("=======================================");
		}
		return -1;
	}

	public Notice getNotice(int index_num) {

		final String SQL = "SELECT Notice.notice_num, "
				+ "Notice.notice_title,"
				+ "Notice.member_id,"
				+ "NoticeContents.notice_contents,"
				+ "Notice.notice_date,"
				+ "Notice.notice_count "
				+ "FROM Notice "
				+ "JOIN NoticeContents "
				+ "ON Notice.notice_num=NoticeContents.notice_num "
				+ "WHERE Notice.notice_num = "+index_num;

		Notice notice = null;
		try {
			stmt = con.prepareStatement(SQL+";");

			ResultSet rs = stmt.executeQuery();
			rs.next();
			notice = new Notice();
			notice.setMember_id(rs.getString("Member_id"));
			notice.setNotice_title(rs.getString("notice_title"));
			notice.setNotice_contents(rs.getString("notice_contents"));
			notice.setNotice_date(rs.getDate("notice_date"));
			notice.setNotice_count(rs.getInt("notice_count"));
			notice.setNotice_num(rs.getInt("notice_num"));
		} catch (SQLException e) {
			System.err.println("=======================================");
			System.err.println("NoticeDAO.getNotice()");
			System.err.println(SQL);
			System.err.println(e.getErrorCode());
			System.err.println("=======================================");
		}
		return notice;
	}
}
