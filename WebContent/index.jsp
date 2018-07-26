<%@page import="database.Notice.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.Notice.NoticeDAO"%>
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
			action="./" method="get">
			<div class="form-group">
				<label for="search" class="sr-only">search</label> <input
					type="text" class="form-control input-group" id="search"
					placeholder="title" name="search">
			</div>
			<button class="btn btn-default" id="serchbtn" type="submit">검색</button>
		</form>
		<c:out value="${maxPage}"></c:out>
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

				<c:forEach var='i' begin='1' end='10' step='1'>

					<li class="page-item "><a class="page-link" role="button"
						href="?page= ${i}"> <c:out value=" ${i}"></c:out>
					</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>