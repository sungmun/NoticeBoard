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
<style type="text/css">
#login-form>div {
	margin: 15px 0;
}
</style>
</head>
<body style="padding-top: 70px;">
	<nav class="navbar navbar-inverse navbar-fixed-top" id="navbar"
		onmouseover="navbarOpen(this)" onmouseout="scrollFunction()">
		<%@ include file="/JavaServerPage/Topbar.jsp"%>
	</nav>
	<div class="container">
		<div class="main">
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-success">
					<div class="panel-heading">
						<div class="panel-title">환영합니다!</div>
					</div>
					<div class="panel-body">
						<form id="login-form"
							action="${pageContext.request.contextPath}/SingUp" method="post">
							<div>
								<input type="text" class="form-control" name="id"
									placeholder="ID" autofocus>
							</div>
							<div>
								<input type="password" class="form-control" name="password"
									placeholder="Password">
							</div>
							<div>
								<button type="submit" class="form-control btn btn-primary">로그인</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>