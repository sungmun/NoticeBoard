<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/NoticeBoard/public/js/default.js"></script>

<c:forEach var="jsItem" items="${paramValues.js}">
	<script src="${jsItem}"></script>
</c:forEach>

