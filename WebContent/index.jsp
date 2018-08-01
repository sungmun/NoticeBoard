
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/head.jsp"%>
<body style="padding-top: 70px;">
	<%@ include file="/JavaServerPage/Topbar.jsp"%>

	<div class="container">
		<form class="form-inline pull-right" style="padding-bottom: 10px"
			method="post">
			<div class="form-group">
				<label for="search" class="sr-only">search</label> <input
					type="text" class="form-control input-group" id="search"
					placeholder="title" name="search">
			</div>
			<button class="btn btn-default" id="serchbtn" type="button">검색</button>
		</form>

		<table class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th width="5%">번호</th>
					<th width="65%">제목</th>
					<th width="20%">작성자</th>
					<th width="10%">작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${noticeList}">

					<tr class="notice_list"
						onclick="location.href = '${pageContext.request.contextPath}/JavaServerPage/NoticeContentPage.jsp?id=${list.notice_num}'">
						<td><c:out value="${list.notice_num}"></c:out></td>
						<td><c:out value="${list.notice_title}"></c:out></td>
						<td><c:out value="${list.member_id}"></c:out></td>
						<td><c:out value="${list.notice_date}"></c:out></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<a
			href="${pageContext.request.contextPath}/JavaServerPage/WritePage.jsp"
			class="pull-right btn btn-default write">글쓰기</a>
		<div class="alert-link text-center">
			<ul class="pagination justify-content-center">
				<li class="page-item  active"><a class="page-link"
					role="button" onclick="pageChange(1);"> <c:out value="1"></c:out>
				</a></li>
				<c:forEach var='i' begin='2' end='10' step='1'>

					<li class="page-item"><a class="page-link" role="button"><c:out value="${i}"></c:out></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
	
	var search='';
	var page=1;
	var maxPage;
	window.onload = () => {maxPage="${maxPage}"};
	$('#serchbtn').on('click',function(){
		search=$('#search').val();
		
		listLoad(1,search);
	});
	$('.page-link').on('click',function(){
		var page=$(event.target).html();
		maxPageLoad(page);
		
		listLoad(page);
	});
	$(document).on('click','.page-link',function(){
		var page=$(event.target).html();
		maxPageLoad(page);
		
		listLoad(page);
	});
	function maxPageLoad(page) {
		var minNum=(page-4<=0)?1:page-4;
		var maxNum=(minNum+9>=maxPage)?maxPage:minNum+9;
		var contentStr='';

		for(var num=minNum;num<=maxNum;num++){
			contentStr+='<li class="page-item'+(((num == page)?' active':''))+'">'
			contentStr+='<a class="page-link" role="button">'+num+'</a></li>';
		}
		$('.pagination').html(contentStr);
	}
	function listLoad(number) {
		var num={"page":number.trim(), "search":search};
		$.ajax({
			url: "/NoticeBoard/ListLoad",
			data:num,
			method :"POST",
			dataType: "JSON",
			fail: ()=>console.log('post['+number+'] error'),
			complete : function(data){
				
				data=JSON.parse(JSON.stringify(data)).responseJSON;
				maxPage=(data.maxPage==null)?maxPage:data.maxPage;
				listChange(JSON.parse(data.list));
			}
		});
	}
	function listChange(list){
		//var list=JSON.parse(JSON.stringify(json));
		//list=JSON.parse(list);

		var listlen=list.length;
		var contentStr='';

		for(var i=0;i<listlen;i++){
			
			contentStr+='<tr class="notice_list" onclick="location.href = "NoticeBoard/JavaServerPage/NoticeContentPage.jsp?id='+list[i].notice_num+'">';
			contentStr+='<td>'+list[i].notice_num+'</td>';
			contentStr+='<td>'+list[i].notice_title+'</td>';
			contentStr+='<td>'+list[i].member_id+'</td>';
			contentStr+='<td>'+list[i].notice_date+'</td></tr>';
			
		}
		$("tbody").html(contentStr);
	}
	
	</script>
</body>
</html>