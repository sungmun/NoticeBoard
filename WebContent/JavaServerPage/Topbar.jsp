<%@page import="database.User.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style>
.member {
	text-align: right;
}

.member button {
	margin: 10px;
}
</style>
</head>
<body>
	<div class="info">
		<div class="member">
			<%
				User user = (User) session.getAttribute("login");
				if (user == null) {
			%>
			<button class="login btn-group-vertical btn" onclick="signIn()">sign
				in</button>
			<button class="sing_up btn-group-vertical btn">sign up</button>
			<%
				} else {
			%>
			<label>ID : <%=user.getId()%></label>
			<button class="sing_up btn-group-vertical btn">MyPage</button>
			<%}%>
		</div>
	</div>
	<script type="text/javascript">
		function signIn() {
			location.href = './JavaServerPage/SingIns.jsp'
		}
	</script>
	<script src="../js/jquery-3.3.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>