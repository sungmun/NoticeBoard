<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="database.Notice.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@include file="/head.jsp"%>
<body style="padding-top: 70px;">
	<%@ include file="/JavaServerPage/Topbar.jsp"%>
	<div class="container">
		<div class="main">

			<div class="col-sm-10 section">
				<header>
					<h2 class="post_title">${notice.notice_title}</h2>
					<p class="post-meta" style="color: #999;">
						${notice.notice_date} <a href="#">${notice.member_id}</a>
					</p>
				</header>
				<hr>
				<p class="panel-body panel-default ">${notice.notice_contents}</p>

			</div>


			<div class="panel section col-sm-10">
				<h3 class="">Comment</h3>
				<hr>
				<div id="comment">
					<div></div>
				</div>
			</div>
			<div class="panel section comment col-sm-10">
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
	<script type="text/javascript">
	var postNum='';
	window.onload=function(){
		postNum='${notice.notice_num}';
		
		commentLoad();
	}
	$("#send").click(function() {
		$.post('/NoticeBoard/WriteComment',{data: {post:'${notice.notice_num}',contents:$('#comment-area').val()}});
		/*
		$.ajax({
			type:"POST",
			url: "/NoticeBoard/WriteComment",
			data: {
				"post":'${notice.notice_num}',
				"contents":$("#comment-area").val()
				},
			dataType: "json",
			fail: ()=>console.log('post['+postNum+'] error'),
			
		});*/
		commentLoad('${notice.notice_num}');
	});
	function commentWrite(list){
		$('#comment>div').remove();
		$.each(list,function(index,comment){
			var total=$('<div/>');
			
			var post_meat=$('<div/>',{class:'post-meta'});
			
			var memberid=$('<div/>',{class:'pull-left'});
			
			memberid.append($('<a/>',{
				href:'#',
				text: comment.memberId
			}));
			post_meat.append(memberid);
			
			var commentday=$('<div/>',{
				class:'pull-right',
				text: comment.commentDay
			});
			post_meat.append(commentday);
			
			total.append(post_meat);
			total.append($('<br/>'));
			
			var commentContent=$('<section/>',{
				class: 'panel-body panel-default',
				text: comment.commentContents
			});
			
			total.append(commentContent);
			
			$('#comment').append(total);
		});
		
		
	}
	
	function commentLoad() {
		$.ajax({
			type:"POST",
			url: "/NoticeBoard/ReadComment",
			data: {"post":postNum},
			dataType: "json",
			fail: ()=>console.log('post['+postNum+'] error'),
			success: (data)=>commentWrite(JSON.parse(data.list))
		});
	}
	</script>
</body>
</html>