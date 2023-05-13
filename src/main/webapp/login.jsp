<%@page import="model.entity.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	AdminBean admin = (AdminBean)session.getAttribute("admin");
	String id = (String)request.getAttribute("id");
	String pw = (String)request.getAttribute("pw");
	String err = (String)request.getAttribute("err");
	String idErr = (String)request.getAttribute("idErr");
	String pwErr = (String)request.getAttribute("pwErr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
	.err {
		color: red;
		text-align: center;
	}
</style>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="headerLogin.jsp" %>

	<%if(!"".equals(idErr) || !"".equals(pwErr) || !"".equals(err)) { %>
		<h1 class="err"><%=err %></h1>
	<%} %>
	<h1>ログイン</h1>
	<hr>
	<form action="login" method="post">
		ユーザーID：
		<%if (!"".equals(id)){ %>
			<input type="text" name="id" value=<%=id %>>
		<%} else { %>
			<input type="text" name="id">
		<%} %>
		<%if(!"".equals(idErr)) { %>
			<div class="err"><%=idErr %></div>
		<%} %>
		<br>
		パスワード：
		<%if (!"".equals(pw)){ %>
			<input type="password" name="pw" value=<%=pw %>>
		<%} else { %>
			<input type="password" name="pw">
		<%} %>
		<%if(!"".equals(pwErr)) { %>
			<div class="err"><%=pwErr %></div>
		<%} %>
		<br><br>
		<input type="hidden" name="process" value="end">
		<input type="submit"  value="ログイン">
		<input type="submit" name="button" value="クリア">
	</form>
	<%@include file="footer.jsp" %>
</body>
</html>