package database.Notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		final int limit = 20;
		final String SQL = "SELECT * FROM Notice ORDER BY notice_num desc LIMIT ?, 20";
		
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
			errMessageprint(e, "NoticeDAO.getNoticeList()");
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
			errMessageprint(e, "NoticeDAO.getNoticeCount()");
		}
		return -1;
	}

	public Notice getNotice(int index_num) {

		final String SQL = "SELECT Notice.notice_num, " + "Notice.notice_title," + "Notice.member_id,"
				+ "NoticeContents.notice_contents," + "Notice.notice_date," + "Notice.notice_count " + "FROM Notice "
				+ "JOIN NoticeContents " + "ON Notice.notice_num=NoticeContents.notice_num "
				+ "WHERE Notice.notice_num = " + index_num;

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
		} catch (SQLException e) {
			errMessageprint(e, "NoticeDAO.getNotice()");
		}
		return notice;
	}

	public boolean insertNotice(Notice notice) {
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
			SQL = "INSERT INTO NoticeContents VALUE(?, ?)";
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, pk);
			stmt.setString(2, notice.getNotice_contents());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			errMessageprint(e, "NoticeDAO.insertNotice(" + notice + ")");
		}
		return false;
	}

	public void errMessageprint(Exception e, String callMethod) {
		System.err.println("=======================================");
		System.err.println(callMethod);
		System.err.println(e.getMessage());
		if (e instanceof SQLException) {
			System.err.println(((SQLException) e).getSQLState());
		}
		System.err.println("=======================================");
	}
}
