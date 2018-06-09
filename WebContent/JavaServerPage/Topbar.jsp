<%@page import="database.User.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
.member button {
	margin: 10px;
}

#navbar {
	transition: top 2s;
}
#navbar:hover {
	top:0;
}
</style>
<div class="container">

	<div class="member collapse navbar-collapse" >
		<div class="navbar-header">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/index.jsp">NoticeBorad</a>
		</div>
		<div class="navbar-right member">
			<%
				User user = (User) session.getAttribute("login");
				if (user == null) {
			%>
			<button class="login btn btn-group"
				onclick="location.href = '${pageContext.request.contextPath}/JavaServerPage/SingIns.jsp'">sign
				in</button>
			<button class="sing_up btn btn-group"
				onclick="location.href = '${pageContext.request.contextPath}/JavaServerPage/SingUps.jsp'">sign
				up</button>
			<%
				} else {
			%>
			<label class="id"><%=user.getId()%></label>
			<button class="sing_up btn-group-vertical btn">MyPage</button>
			<%
				}
			%>
		</div>
	</div>
</div>
<script type="text/javascript">
	function navbarOpen(bar) {
		bar.style.top="0";
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