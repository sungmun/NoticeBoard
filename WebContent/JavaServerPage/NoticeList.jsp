<%@page import="java.util.ArrayList"%>
<%@page import="database.Notice.NoticeDAO"%>
<%@page import="database.Notice.Notice"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form class="form-inline pull-right" style="padding-bottom: 10px">
	<div class="form-group">
		<label for="search" class="sr-only">search</label> <input type="text"
			class="form-control input-group" id="search" placeholder="title">
	</div>
	<button class="btn btn-default" id="serchbtn">검색</button>
</form>
<table class="table table-bordered table-striped table-hover">
	<thead>
		<tr>
			<th width="10%">번호</th>
			<th width="60%">제목</th>
			<th width="10%">작성자</th>
			<th width="20%">작성날짜</th>
		</tr>
	</thead>
	<tbody>
		<%
			NoticeDAO DAO = NoticeDAO.createNoticeDAO();
			ArrayList<Notice> list = DAO.getNoticeList(1);
			for (Notice e : list) {
		%>
		<tr class="notice_list"
			onclick="location.href = '${pageContext.request.contextPath}/JavaServerPage/NoticeContentPage.jsp?id='+this.cells[0].innerHTML">
			<td><%=e.getNotice_num()%></td>
			<td><%=e.getNotice_title()%></td>
			<td><%=e.getMember_id()%></td>
			<td><%=e.getNotice_date()%></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<a href="${pageContext.request.contextPath}/JavaServerPage/WritePage.jsp" class="pull-right btn btn-default write">글쓰기</a>
<div class="alert-link text-center">
	<a href="#" onclick="">1</a>
</div>

