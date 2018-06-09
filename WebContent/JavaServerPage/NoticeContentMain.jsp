<%@page import="database.Notice.NoticeDAO"%>
<%@page import="database.Notice.Notice"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 
<style type="text/css">
textarea.form-control {
	height: 100;
}

div.section {
	display: block;
	box-shadow: 4px 4px 4px gray;
	border: 1px;
	border-style: solid;
	border-color: lightgray;
}

.title {
	display: block;
	padding-left: 5px;
	border-bottom: 2px;
	border-bottom-style: solid;
	border-bottom-color: lightgray;
	padding: 2px;
	text-align: center;
}

.title h2 {
	margin-top: 10px;
	margin-bottom: 10px;
}

#title_attribute {
	padding: 5px 5px 5px 5px;
	border-bottom-color: lightgray;
	border-bottom: 1px;
	border-bottom-style: solid;
}

#title_attribute>.author_id {
	width: 49%;
	display: inline-block;
	left: 0px;
	text-align: left;
}

#title_attribute>.notice_date {
	width: 49%;
	display: inline-block;
	right: 0px;
	text-align: right;
}

.notice_contents {
	padding: 10px 10px 10px 10px;
}

.comment {
	margin-bottom: 10px;
	text-align: right;
}

#comment {
	border-color: white;
	overflow-y: hidden;
	height: 100px;
}

#comment:focus {
	outline: none;
	box-shadow: inset 0 1px 1px white, 0 0 8px white;
}
</style>
 -->
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		id = (id == null) ? "1" : id;
		NoticeDAO DAO = NoticeDAO.createNoticeDAO();
		Notice notice = DAO.getNotice(Integer.parseInt("1"));
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
				<textarea name="comment" class="custom-control form-control comment"
					placeholder="comment" row="3" style = "resize : none"></textarea>
				<span class="input-group-addon btn btn-primary">Send
				<!-- <button type="button" class="btn btn-default">comment</button> -->
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
</body>
</html>