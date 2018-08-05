<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/public/head.jsp"></c:import>
</head>
<body>
	<c:import url="/public/Topbar.jsp"></c:import>
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
	
</body>
</html>