<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page-header">
	<h1>회원가입</h1>
</div>
<div class="col-md-6 col-md-offset-3">
	<form role="form" action="${pageContext.request.contextPath}/Singin" method="post">
		<div class="form-group">
			<label for="id">ID</label>
			<div class="input-group">
				<input type="text" class="form-control" name="id" placeholder="ID">
				<span class="input-group-btn">
					<button class="btn btn-success">중복확인</button>
				</span>
			</div>
		</div>
		<div class="form-group">
			<label for="InputPassword1">비밀번호</label> <input type="password"
				class="form-control" name="password" placeholder="비밀번호">
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
			<label for="InputEmail">이메일 주소</label> <input type="email"
				class="form-control" name="email" placeholder="이메일 주소">
		</div>
		<div class="form-group">
			<label for="username">휴대폰 번호</label> <input type="tel"
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
