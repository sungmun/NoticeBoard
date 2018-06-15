<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NoticeBoard</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</head>
<body style="padding-top: 70px;">
<%
	if(request.getSession().getAttribute("login")==null){
		response.sendRedirect("./SingUpPage.jsp");
	}
%>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="navbar" onmouseover="navbarOpen(this)" onmouseout="scrollFunction()">
		<%@ include file="/JavaServerPage/Topbar.jsp"%>
	</nav>
	<div class="container">
		<%@ include file="/JavaServerPage/WriteMain.jsp"%>
	</div>
</body>
</html>