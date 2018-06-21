<%@page import="database.User.UserDAO"%>
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
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body style="padding-top: 70px;">
	<nav class="navbar navbar-inverse navbar-fixed-top" id="navbar"
		onmouseover="navbarOpen(this)" onmouseout="scrollFunction()">
		<%@ include file="/JavaServerPage/Topbar.jsp"%>
	</nav>
	<%
		User users = (User) request.getSession().getAttribute("login");
		if (users == null) {
			response.sendRedirect("./SingUpPage.jsp");
		}
	%>
	<div class="container">
		<div class="main">
			<div class="page-header">
				<h1>개인 정보</h1>
			</div>
			<div class="col-md-6 col-md-offset-3">
				<form>
					<div class="form-group">
						<label for="id">ID</label> <input type="text" class="form-control"
							disabled="disabled" value=<%=users.getId()%>>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="text"
							class="form-control" disabled="disabled" name="password"
							id="password">
					</div>
					<div class="form-group">
						<label for="password2">Password</label> <input type="text"
							class="form-control" id="password2">
					</div>
					<div class="form-group">
						<label for="username">이름</label>
						<div class="row">
							<div class="col-md-6">
								<input type="text" class="form-control" name="firstname"
									placeholder="FirstName" disabled="disabled"
									value=<%=users.getFirstname()%>>
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" name="secondname"
									placeholder="SecondName" disabled="disabled"
									value=<%=users.getSecondname()%>>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="email">이메일 주소</label> <input type="email"
							class="form-control" placeholder="이메일 주소"
							value=<%=users.getEmail()%>>
					</div>
					<div class="form-group">
						<label for="phone">휴대폰 번호</label> <input type="tel"
							class="form-control" placeholder="- 없이 입력해 주세요"
							value=<%=users.getPhone()%>> <span
							class="input-group-btn"> </span>
					</div>
					<%
						if (request.getSession().getAttribute("login") != null) {
					%>
					<div class="form-group text-center">
						<button type="button" class="btn btn-info">
							수정하기<i class="fa fa-check spaceLeft"></i>
						</button>
					</div>
					<%
						}
					%>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function passwordCheck() {
		if(form.InputPassword==""||form.InputPassword2==""){
			return false;
		}
		if (form.InputPassword2 == form.InputPassword) {
			return true;
		}
		$('#passwordModal').modal();
		return false;
	}
	</script>
</body>
</html>