package database.Comment;

public class Comment {
	/*
	 * CREATE TABLE Comment( notice_num INT(12) unsigned NOT NULL, member_id
	 * VARCHAR(20) NOT NULL, comment_contents VARCHAR(255) NOT NULL, FOREIGN KEY
	 * (member_id) REFERENCES User (user_id) ON DELETE RESTRICT, FOREIGN KEY
	 * (notice_num) REFERENCES Notice (notice_num) ON DELETE CASCADE ON UPDATE
	 * CASCADE );
	 * ALTER TABLE Comment ADD comment_day DATETIME;
	 */
	private int noticeNum;
	private String memberId;
	private String commentContents;
	private String commentDay;
	
	
	
	public String getCommentDay() {
		return commentDay;
	}

	public void setCommentDay(String commentDay) {
		this.commentDay = commentDay;
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
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

	@Override
	public String toString() {
		String json=null;
		json+="{";
		
		json+="\"noticeNum\" : "+noticeNum;
		
		json+=", \"memberId\" : "+memberId;
		json+=", \"commentContents\" : "+commentContents;
		json+=", \"commentDay\" : "+commentDay;
		json+="}";
		return json;
	}
}
