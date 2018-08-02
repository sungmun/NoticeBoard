<%@page import="database.Notice.Notice"%>
<%@page import="database.Notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@include file="/head.jsp"%>
<body style="padding-top: 70px;">
	<%@ include file="/JavaServerPage/Topbar.jsp"%>
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
					<h2 class="post_title"><jsp:getProperty name="notice" property="Notice_title"></h2>
					<p class="post-meta" style="color: #999;"><jsp:getProperty name="notice" property="notice_date">
						<a href="#"><jsp:getProperty name="notice" property="member_id"></a>
					</p>
				</header>
				<hr>
				<p class="panel-body panel-default "><jsp:getProperty name="notice" property="notice_contents"></p>

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
						<textarea name="comment" id="comment-area"
							class="custom-control form-control comment" placeholder="comment"
							style="resize: none"></textarea>
						<span class="input-group-addon btn btn-primary" id="send">Send <!-- <button type="button" class="btn btn-default">comment</button> -->
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	window.onload=commentLoad(<%=id%>);
	
	$("#send").click(function() {
		$.ajax({
			type:"POST",
			url: "/NoticeBoard/WriteComment",
			data: {
				"post":<%=id%>,
				"contents":$("#comment-area").val()
				},
			dataType: "html",
			fail: ()=>console.log('post['+postNum+'] error'),
			success: (data)=>$('#comment').html(data)
		});
		commentLoad(<%=id%>);
	});
	
	function commentLoad(postNum) {
		$.ajax({
			type:"POST",
			url: "/ListLoad/ReadComment",
			data: {"post":postNum},
			dataType: "html",
			fail: ()=>console.log('post['+postNum+'] error'),
			success: (data)=>$('#comment').html(data)
		});
	}
	</script>
</body>
</html>