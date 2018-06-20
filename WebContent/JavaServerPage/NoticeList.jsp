<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.Notice.NoticeDAO"%>
<%@page import="database.Notice.Notice"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pageNum = request.getParameter("page");
	pageNum = (pageNum == null) ? "1" : pageNum;
	NoticeDAO DAO = NoticeDAO.createNoticeDAO();

	final int noticeCount;
	final String search=request.getParameter("search");
	ArrayList<Notice> list;
	if(search==null){
		list = DAO.getNoticeList(Integer.parseInt(pageNum));
		noticeCount=DAO.getNoticCount();
	}else{
		list=DAO.getNoticeList("SELECT * FROM Notice WHERE notice_title Like \"%"+search+"%\" ORDER BY notice_num desc LIMIT ?, 20",Integer.parseInt(pageNum));
		noticeCount=DAO.getNoticCount("SELECT COUNT(*) as cnt FROM Notice WHERE Notice.notice_title Like \"%"+search+"%\"");
	}
	final int MAX_PAGE=noticeCount/20+(noticeCount%20>0?1:0);
%>
<form class="form-inline pull-right" style="padding-bottom: 10px" action="./" method="get">
	<div class="form-group">
		<label for="search" class="sr-only">search</label> <input type="text"
			class="form-control input-group" id="search" placeholder="title" name="search">
	</div>
<button class="btn btn-default" id="serchbtn" type="submit" >검색</button>
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
		<%
			for (Notice e : list) {
		%>
		<tr class="notice_list"
			onclick="location.href = '${pageContext.request.contextPath}/JavaServerPage/NoticeContentPage.jsp?id=<%=e.getNotice_num()%>'">
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
<a
	href="${pageContext.request.contextPath}/JavaServerPage/WritePage.jsp"
	class="pull-right btn btn-default write">글쓰기</a>
<div class="alert-link text-center">
	<ul class="pagination justify-content-center">
		<%
			int[] pageNums = new int[9];
			int index = 0;
			for (int i = -4; i < 5; i++) {
				pageNums[index++] = Integer.parseInt(pageNum) - i;
			}
			for (int i = index - 1; i >= 0; i--) {
				if (pageNums[i] <= 0 || pageNums[i] > MAX_PAGE) {
					continue;
				}
		%>
		<li
			class="page-item <%=(pageNums[i] == Integer.parseInt(pageNum)) ? "active" : ""%>">
			<a class='page-link'  role="button" href='?page=<%=pageNums[i]%><%=((search==null)?"":"&search="+search)%>'> <%=pageNums[i]%>
		</a>
		</li>
		<%
			}
		%>
	</ul>
</div>

