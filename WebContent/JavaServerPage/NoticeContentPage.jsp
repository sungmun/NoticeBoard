<%@page import="database.Notice.Notice"%>
<%@page import="database.Notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NoticeBoard</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body style="padding-top: 70px;">
	<nav class="navbar navbar-inverse navbar-fixed-top" id="navbar"
		onmouseover="navbarOpen(this)" onmouseout="scrollFunction()">
		<%@ include file="/JavaServerPage/Topbar.jsp"%>
	</nav>
	<div class="container">
		<div class="main">
			<%
				request.setCharacterEncoding("UTF-8");

				String id = request.getParameter("id");
				id = (id == null) ? "1" : id;
				NoticeDAO DAO = NoticeDAO.createNoticeDAO();
				Notice notice = DAO.getNotice(Integer.parseInt(id));
			%>
			<div class="col-sm-10 section">
				<header>
					<h2 class="post_title"><%=notice.getNotice_title()%></h2>
					<p class="post-meta" style="color: #999;"><%=notice.getNotice_date()%>
						<a href="#"><%=notice.getMember_id()%></a>
					</p>
				</header>
				<hr>
				<p class="panel-body panel-default "><%=notice.getNotice_contents()%></p>
				<hr>
			</div>
			<div class="panel section comment col-sm-10">
				<div class="form-group">
					<label class="sr-only">comment</label>
					<div class="input-group">
						<textarea name="comment"
							class="custom-control form-control comment" placeholder="comment"
							 style="resize: none"></textarea>
						<span class="input-group-addon btn btn-primary">Send <!-- <button type="button" class="btn btn-default">comment</button> -->
						</span>
					</div>
				</div>
			</div>

			<div class="panel section col-sm-10">
				<div id="title_attribute" class="post-meta text-right">
					<p>
						2017-01-01 <a href="#">tjdans174</a>
					</p>
				</div>
				<section class="panel-body panel-default ">댓글이 들어갈 공간입니다</section>
			</div>
		</div>
	</div>
	<script src="../js/jquery-3.3.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>