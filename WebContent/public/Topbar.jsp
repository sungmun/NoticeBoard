<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse navbar-fixed-top" id="navbar">
	<div class="container">
		<div class="member collapse navbar-collapse">
			<div class="navbar-header">
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/index">NoticeBorad</a>
			</div>
			<div class="navbar-right member">
				<c:choose>
					<c:when test="${login eq null}">
						<button class="sing_up btn btn-group" id="signUp">sign up</button>
						<button class="login btn btn-group" id="signIn">sign in</button>
					</c:when>
					<c:otherwise>
						<label class="id" style="color: white">${login.id}</label>
						<a class="sing_up btn btn-group-vertical"
							href="/NoticeBoard/JavaServerPage/Mypage/MyPage.jsp">MyPage</a>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>
</nav>
