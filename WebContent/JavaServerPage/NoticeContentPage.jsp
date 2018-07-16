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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body style="padding-top: 70px;" >
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

			</div>


			<div class="panel section col-sm-10">
				<h3 class="">Comment</h3>
				<hr>
				<div id="comment">
					
				</div>
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
		</div>
	</div>
	<script type="text/javascript">
	window.onload=commentLoad(<%=id%>);
	function commentLoad(postNum) {
		$.ajax({
			type:"POST",
			url: "/NoticeBoard/WriteComment",
			data: {"post":postNum},
			dataType: "html",
			fail: ()=>console.log('post['+postNum+'] error'),
			success: (data)=>{
				$('#comment').html(data);
			}
		});
	}
	</script>
</body>
</html>