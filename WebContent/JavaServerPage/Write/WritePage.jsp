<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/public/head.jsp"></c:import>
</head>
<body>
	<c:import url="/public/Topbar.jsp"></c:import>
	<c:if test="${login eq null}">
		<c:redirect url="/JavaServerPage/SingUp/SingUpPage.jsp"></c:redirect>
	</c:if>

	<div class="container">
		<form action="${pageContext.request.contextPath}/Write" method="post"
			class="for">
			<div class="form-group">
				<label for="title">Title</label> <input type="text" name="title"
					id="title" class="form-control">
			</div>
			<div class="form-group">
				<label for="content">Content</label>
				<textarea name="content" id="editor" class="form-control"></textarea>
			</div>
			<div class="form-group">
				<label for="upload">Upload</label>
				<input type="file" name="upload" id="upload" class="form-control">
			</div>
			<div class="pull-right">
				<button type="submit" class="btn btn-default">Sucess</button>
			</div>
		</form>
	</div>
	<c:import url="/public/tail.jsp">
		<c:param name="js" value="/NoticeBoard/ckeditor4/ckeditor.js"></c:param>
		<c:param name="js"
			value="/NoticeBoard/JavaServerPage/Write/js/default.js"></c:param>
	</c:import>
</body>
</html>