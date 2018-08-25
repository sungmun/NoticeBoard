<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/public/head.jsp"></c:import>
</head>
<body>
	<c:import url="/public/Topbar.jsp"></c:import>
	<div id="maxPage" style="display:none;">${maxPage}</div>
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

					<tr class="notice_list">
						<td><c:out value="${list.notice_num}"></c:out></td>
						<td><c:out value="${list.notice_title}"></c:out></td>
						<td><c:out value="${list.member_id}"></c:out></td>
						<td><c:out value="${list.notice_date}"></c:out></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<a
			href="/NoticeBoard/JavaServerPage/Write/WritePage.jsp"
			class="pull-right btn btn-default write">글쓰기</a>
		<div class="alert-link text-center">
			<ul class="pagination justify-content-center">
				<li class="page-item  active"><a class="page-link"
					role="button" onclick="pageChange(1);"> <c:out value="1"></c:out>
				</a></li>
				<c:forEach var='i' begin='2' end='10' step='1'>

					<li class="page-item"><a class="page-link" role="button"><c:out
								value="${i}"></c:out></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<c:import url="/public/tail.jsp">
		<c:param name="js"
			value="/NoticeBoard/js/indexJs.js"></c:param>
	</c:import>
</body>
</html>