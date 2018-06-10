<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NoticeBoard</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body style="padding-top: 70px;">

	<nav class="navbar navbar-inverse navbar-fixed-top" id="navbar" onmouseover="navbarOpen(this)" onmouseout="scrollFunction()">
		<%@ include file="/JavaServerPage/Topbar.jsp"%>
	</nav>
	<div class="container">
		<div class="main">
			<%@ include file="/JavaServerPage/SingInMain.jsp"%>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

</html>