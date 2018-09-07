package database.Notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exception.SQLCustomException;
import database.DataAcessObject;

public class NoticeDAO extends DataAcessObject {
	private static NoticeDAO InstanseNoticDAO;

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
		return getNoticeList("SELECT * FROM Notice ORDER BY notice_num desc LIMIT ?, 20", page);
	}

	public ArrayList<Notice> getNoticeList(final String SQL, int page) {
		final int limit = 20;

		ArrayList<Notice> array = new ArrayList<>();
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, limit * (page - 1));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setMember_id(rs.getString("Member_id"));
				notice.setNotice_title(rs.getString("notice_title"));
				notice.setNotice_date(rs.getDate("notice_date"));
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setNotice_count(rs.getInt("notice_count"));
				array.add(notice);
			}
		} catch (SQLException e) {
			throw new SQLCustomException("NoticeDAO.getNoticeList()", e);
		}

		return array;
	}

	public int getNoticCount(final String SQL) {
		try {
			stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cnt");
			}
		} catch (SQLException e) {
			throw new SQLCustomException("NoticeDAO.getNoticeCount()", e);
		}
		return -1;
	}

	public int getNoticCount() {
		return getNoticCount("SELECT COUNT(*) as cnt FROM Notice");
	}

	public Notice getNotice(int index_num) {

		final String SQL = "SELECT Notice.notice_num, " + "Notice.notice_title," + "Notice.member_id,"
				+ "NoticeContents.notice_contents," + "Notice.notice_date," + "Notice.notice_count, "
				+ "NoticeContents.fileName " + "FROM Notice " + "JOIN NoticeContents "
				+ "ON Notice.notice_num=NoticeContents.notice_num " + "WHERE Notice.notice_num = " + index_num;
		Notice notice = null;
		try {
			stmt = con.prepareStatement(SQL);

			ResultSet rs = stmt.executeQuery();
			rs.next();
			notice = new Notice();
			notice.setMember_id(rs.getString("Member_id"));
			notice.setNotice_title(rs.getString("notice_title"));
			notice.setNotice_contents(rs.getString("notice_contents"));
			notice.setNotice_date(rs.getDate("notice_date"));
			notice.setNotice_count(rs.getInt("notice_count"));
			notice.setNotice_num(rs.getInt("notice_num"));
			notice.setFile_name(rs.getString("fileName"));
		} catch (SQLException e) {
			throw new SQLCustomException("NoticeDAO.getNotice()", e);
		}
		return notice;
	}

	public int insertNotice(Notice notice) {
		String SQL = "INSERT INTO Notice(notice_title, member_id) VALUE(?, ?)";
		try {
			stmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, notice.getNotice_title());
			stmt.setString(2, notice.getMember_id());

			stmt.execute();

			int pk = -1;
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				pk = rs.getInt(1);
			}
			SQL = "INSERT INTO NoticeContents (notice_num,notice_contents"
					+ ((notice.getFile_name() == null) ? "" : ",fileName") + ") VALUE(?, ?"
					+ ((notice.getFile_name() == null) ? "" : ", ?") + ")";

			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, pk);
			stmt.setString(2, notice.getNotice_contents());
			if (notice.getFile_name() != null)
				stmt.setString(3, notice.getFile_name());
			stmt.execute();
			return pk;
		} catch (SQLException e) {
			throw new SQLCustomException("NoticeDAO.insertNotice(" + notice + ")", e);
		}
	}
}
