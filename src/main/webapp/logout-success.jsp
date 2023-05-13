<%@page import="model.entity.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%AdminBean admin = (AdminBean)request.getAttribute("admin");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@include file="headerLogin.jsp" %>
<h1>ログアウトしました</h1>
<hr>
<h2 class="center"><%=admin.getAdminId() %>さん、お疲れさまでした。</h2>
	<form action="login" method="post">
		<input type="submit" value="ログイン画面へ">
	</form>
	<%@include file="footer.jsp" %>
</body>

</html>