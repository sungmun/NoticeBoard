<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style>

</style>
</head>
<body class="media-body">
	<header class="page-header">
		<h2>회원가입</h2>
	</header>
	<section>
		<form action="SingIn.jsp" method="post">
		<table class="table-condensed">
			<tr>
				<td class="text-capitalize">id</td>
				<td colspan="2"><input type="text" name="id" id="user_id" class="input-sm"></td>
			</tr>
			<tr>
				<td class="text-capitalize">password</td>
				<td colspan="2"><input type="password" name="password" id="user_password" autocomplete="new-password" class="input-sm"></td>
			</tr>
			<tr>
				<td class="text-capitalize">name</td>
				<td><input type="text" name="firstname" id="user_firstname" placeholder="First name" class="input-sm" width="10"></td>
				<td><input type="text" name="secondname" id="user_secondname"placeholder="Second name" class="input-sm"></td>
			</tr>
			<tr>
				<td class="text-capitalize">phone</td>
				<td colspan="2"><input type="text" name="phone" id="user_phone"class="input-sm" width="100%"></td>
			</tr>
			<tr>
				<td class="text-capitalize">email</td>
				<td colspan="2"><input type="email" name="email" id="user_email"class="input-sm"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="회원가입" class="center-block btn">				
				</td>
			</tr>
		</table>
	</form>
	</section>
	<script src="../js/jquery-3.3.1.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>