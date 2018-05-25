<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.*" import="database.MySQLDataBaseController"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NoticeBoard</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<style type="text/css">
div.btn-block {
	margin-bottom: 10px;
	text-align: right;
}
</style>
<title>Insert title here</title>
</head>
<body class="modal-body">
	<section>
		<div class="btn-block">
			<input type="text" class="btn-default btn">
			<button class="btn btn-default" id="serchbtn">검색</button>
		</div>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th width="10px">번호</th>
					<th width="50px">제목</th>
					<th width="10px">작성자</th>
					<th width="20px">작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<%
					MySQLDataBaseController db = new MySQLDataBaseController();
					Connection con = db.getConnection();
					if (con != null) {
						Statement stmt = db.getConnection().createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM Notice");
						while (rs.next()) {
				%>
				<tr>
					<td><%=rs.getInt("notice_num")%></td>
					<td><%=rs.getString("notice_title")%></td>
					<td><%=rs.getString("member_id")%></td>
					<td><%=rs.getDate("notice_date")%></td>
				</tr>
				<%
						}
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
	</section>
	<script></script>
	<script src="./js/jquery-3.3.1.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>