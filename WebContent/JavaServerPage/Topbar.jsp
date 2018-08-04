<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="navbar"
		onmouseover="navbarOpen(this)" onmouseout="scrollFunction()">
		<div class="container">
			<div class="member collapse navbar-collapse">
				<div class="navbar-header">
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/index">NoticeBorad</a>
				</div>
				<div class="navbar-right member">
					<c:choose>
						<c:when test="${login eq null}">
							<button class="sing_up btn btn-group"
								onclick="location.href = '${pageContext.request.contextPath}/JavaServerPage/SingUpPage.jsp'">sign
								up</button>
							<button class="login btn btn-group"
								onclick="location.href = '${pageContext.request.contextPath}/JavaServerPage/SingInPage.jsp'">sign
								in</button>
						</c:when>
						<c:otherwise>
							<label class="id" style="color: white">${login.id}</label>
							<a class="sing_up btn btn-group-vertical"
								href="${pageContext.request.contextPath}/JavaServerPage/MyPage.jsp">MyPage</a>
						</c:otherwise>
					</c:choose>
				
				</div>
			</div>
		</div>
	</nav>
	<script type="text/javascript">
		function navbarOpen(bar) {
			bar.style.top = "0";
		}

		window.addEventListener('scroll', function() {
			scrollFunction();
		});

		function scrollFunction() {
			if (document.body.scrollTop > 20
					|| document.documentElement.scrollTop > 20) {

				document.getElementById("navbar").style.top = "-45px";
			} else {
				document.getElementById("navbar").style.top = "0";
			}
		}
	</script>
</body>
</html>