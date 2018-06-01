<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NoticeBoard</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<style type="text/css">
section.main {
	height: 100%;
	margin: 0 auto;
}
	body{
		background-color: slategrey;
	}
</style>
</head>
<body class="modal-body">
	<section style="width: 100%;" class="topbar">
		<%@ include file="/JavaServerPage/Topbar.jsp"%>
	</section>
	<section style="width: 1000px;" class="main">
		<%@ include file="/JavaServerPage/NoticeList.jsp"%>
	</section>
	<script src="./js/jquery-3.3.1.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>