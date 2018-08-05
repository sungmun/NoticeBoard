<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/head.jsp"></c:import>
</head>
<body style="padding-top: 70px;">
	<c:import url="/JavaServerPage/Topbar.jsp"></c:import>
	<c:if test="${login} eq null">
		<c:redirect url="./SingUpPage.jsp"></c:redirect>
	</c:if>
	<div class="container">
		<div class="main">
			<div class="page-header">
				<h1>개인 정보</h1>
			</div>
			<div class="col-md-6 col-md-offset-3">
				<form method="post" onsubmit="return passwordCheck();" name="form"
					role="form" action="${pageContent.request.contextPath}/UpdateUser">
					<div class="form-group">
						<label for="id">ID</label> <input type="text" class="form-control"
							disabled="disabled" value="${login.id}">
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
									value="${login.firstname}">
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" name="secondname"
									placeholder="SecondName" disabled="disabled"
									value="${login.secondname}">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="email">이메일 주소</label> <input type="email"
							class="form-control" placeholder="이메일 주소" value="${login.email}">
					</div>
					<div class="form-group">
						<label for="phone">휴대폰 번호</label> <input type="tel"
							class="form-control" placeholder="- 없이 입력해 주세요"
							value="${login.phone}"> <span class="input-group-btn">
						</span>
					</div>
					<div class="form-group text-center">
						<button type="button" class="btn btn-info">
							수정하기<i class="fa fa-check spaceLeft"></i>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="/JavaServerPage/ErrorMessage.jsp"></c:import>
	<script type="text/javascript">
		function passwordCheck() {
			if (form.InputPassword2 == form.InputPassword) {
				return true;
			}
			showModal("비밀번호가 같지 않거나 비밀번호를 입력하지 않으셨습니다");
			return false;
		}
	</script>
</body>
</html>