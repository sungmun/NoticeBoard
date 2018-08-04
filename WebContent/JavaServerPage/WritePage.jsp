<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/head.jsp"%>
<body style="padding-top: 70px;">
	<%@ include file="/JavaServerPage/Topbar.jsp"%>
	<c:if test="${login} eq null">
		<c:redirect url="./SingUpPage.jsp"></c:redirect>
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
				<textarea name="content" id="editor" class="form-control">
            </textarea>
			</div>
			<div class="pull-right">
				<button type="submit" class="btn btn-default">Sucess</button>
			</div>

		</form>

		<script src="../ckeditor4/ckeditor.js"></script>
		<script type="text/javascript">
			CKEDITOR.replace('editor');
			CKEDITOR.config.height = 600;
		</script>
	</div>
</body>
</html>