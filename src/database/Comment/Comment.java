package database.Comment;

import java.io.Serializable;

public class Comment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9179312563896349898L;
	/*
	 * CREATE TABLE Comment( notice_num INT(12) unsigned NOT NULL, member_id
	 * VARCHAR(20) NOT NULL, comment_contents VARCHAR(255) NOT NULL, FOREIGN KEY
	 * (member_id) REFERENCES User (user_id) ON DELETE RESTRICT, FOREIGN KEY
	 * (notice_num) REFERENCES Notice (notice_num) ON DELETE CASCADE ON UPDATE
	 * CASCADE ); ALTER TABLE Comment ADD comment_day DATETIME;
	 */
	private int noticeNum;
	private int commentNum;
	private int reCommentGroup = 0;
	private String memberId;
	private String commentContents;
	private String commentDay;

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getReCommentGroup() {
		return reCommentGroup;
	}

	public void setReCommentGroup(int reCommentGroup) {
		this.reCommentGroup = reCommentGroup;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getCommentDay() {
		return commentDay;
	}

	public void setCommentDay(String commentDay) {
		this.commentDay = commentDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
