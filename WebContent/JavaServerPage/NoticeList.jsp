<%@page import="java.util.ArrayList"%>
<%@page import="database.Notice.NoticeDAO"%>
<%@page import="database.Notice.Notice"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style type="text/css">
table {
	table-layout: fixed;
}

div.btn-block {
	margin-bottom: 10px;
	text-align: right;
}

table tbody tr.notice_list:hover {
	background-color: gray;
}
</style>
</head>
<body>
	<div class="btn-block">
		<input type="text" class="btn-default btn">
		<button class="btn btn-default" id="serchbtn">검색</button>
	</div>
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th width="10%">번호</th>
				<th width="50%">제목</th>
				<th width="10%">작성자</th>
				<th width="20%">작성날짜</th>
			</tr>
		</thead>
		<tbody>
			<%
				NoticeDAO DAO = NoticeDAO.createNoticeDAO();
				ArrayList<Notice> list = DAO.getNoticeList();
				for (Notice e : list) {
			%>
			<tr class="notice_list" style="cursor: pointer;">
				<td><%=e.getNotice_num() + 1%></td>
				<td><%=e.getNotice_title()%></td>
				<td><%=e.getMember_id()%></td>
				<td><%=e.getNotice_date()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<div class="btn-block">
		<button class="btn btn-default write">글쓰기</button>
	</div>
	<div class="alert-link text-center">
		<a href="#" onclick="">1</a>
	</div>
</body>
</html>