<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="database.Notice.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<c:import url="/public/head.jsp">
	<c:param name="css"
			value="/NoticeBoard/JavaServerPage/Contents/css/default.css"></c:param>
</c:import>
</head>
<body>
	<c:import url="/public/Topbar.jsp"></c:import>
	<div class="container">
		<div class="main">

			<div class="col-sm-10 section" id="post"
				data-noticenum="${notice.notice_num}">
				<header>
					<h2 class="post_title" data-noticTitle="${notice.notice_title}">${notice.notice_title}</h2>
					<p class="post-meta" style="color: #999;">
						${notice.notice_date} <a href="#">${notice.member_id}</a>
					</p>
				</header>
				<hr>
				<c:if test="${notice.file_name ne null}">
					<a class="pull-right file_download" style="color: gray;" href="/NoticeBoard/DownLoad?post=${notice.notice_num}&file=${notice.file_name}" download="${notice.file_name}">
						<span class="glyphicon glyphicon-download-alt"></span>
							${notice.file_name}
					</a>
				</c:if>
				<div class="panel-body panel-default">${notice.notice_contents}</div>
			</div>


			<div class="panel section col-sm-10">
				<h3 class="">Comment</h3>
				<hr>
				<div id="comment">
					<div></div>
				</div>
			</div>
			<div class="panel section comment col-sm-10" id="comment_input">
				<div class="form-group">
					<label class="sr-only">comment</label>
					<div class="input-group">
						<textarea name="comment" id="comment-area"
							class="custom-control form-control comment" placeholder="comment"
							style="resize: none"></textarea>
						<span class="input-group-addon btn btn-primary" id="send">Send
							<!-- <button type="button" class="btn btn-default">comment</button> -->
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/public/ErrorMessage.jsp"></c:import>
	<c:import url="/public/tail.jsp">
		<c:param name="js"
			value="/NoticeBoard/JavaServerPage/Contents/js/default.js"></c:param>
	</c:import>
</body>
</html>