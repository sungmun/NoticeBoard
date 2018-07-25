<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@include file="/head.jsp"%>
<body style="padding-top: 70px;">
	<%@ include file="/JavaServerPage/Topbar.jsp"%>
	<div class="container">
		<div class="main">
			<div class="page-header">
				<h1>회원가입</h1>
			</div>
			<div class="col-md-6 col-md-offset-3">
				<form role="form" action="${pageContent.request.contextPath}/Singin"
					onsubmit="return passwordCheck();" method="post" name="form">
					<div class="form-group">
						<label for="id">ID</label>
						<div class="input-group">
							<input type="text" class="form-control" name="id"
								placeholder="ID"> <span class="input-group-btn">
								<button class="btn btn-success" id="idOverlapCheck">중복확인</button>
							</span>
						</div>
					</div>
					<div class="form-group">
						<label for="password">password</label> <input type="password"
							id="password" class="form-control" name="password"
							placeholder="비밀번호">
					</div>
					<div class="form-group">
						<label for="InputPassword2">비밀번호 확인</label> <input type="password"
							class="form-control" id="InputPassword2" placeholder="비밀번호 확인">
						<p class="help-block">비밀번호 확인을 위해 다시한번 입력 해 주세요</p>
					</div>
					<div class="form-group">
						<label for="username">이름</label>
						<div class="row">
							<div class="col-md-6">
								<input type="text" class="form-control" name="firstname"
									placeholder="FirstName">
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" name="secondname"
									placeholder="SecondName">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="email">이메일 주소</label> <input type="email"
							class="form-control" name="email" placeholder="이메일 주소">
					</div>
					<div class="form-group">
						<label for="phone">휴대폰 번호</label> <input type="tel"
							class="form-control" name="phone" placeholder="- 없이 입력해 주세요">
						<span class="input-group-btn"> </span>
					</div>
					<div class="form-group text-center">
						<button type="submit" class="btn btn-info">
							회원가입<i class="fa fa-check spaceLeft"></i>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true"
		id="passwordModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title">
						<font style="vertical-align: inherit;">경고</font></a>
					</h4>
				</div>
				<div class="modal-body">
					<font style="vertical-align: inherit;"><font
						style="vertical-align: inherit;"> 비밀번호가 같지 않습니다.<br>
							다시 확인해 주세요
					</font></font>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function passwordCheck() {
			if (form.InputPassword2 == form.InputPassword) {
				return true;
			}
			$('#passwordModal').modal();
			return false;
		}
	</script>
</body>

</html>