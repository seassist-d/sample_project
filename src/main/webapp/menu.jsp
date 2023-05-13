<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

	<h1>メニュー画面</h1>
	<hr>
	
	<form action="item-list" method="post">
		<input type="text" name="seachWord" placeholder="例：ピース">
		<input type="submit" name="button" value="検索">
	</form>
	<br>
	<form action="menu" method="post">
		<input type="submit" name="button" value="商品登録"><br><br>
		<input type="submit" name="button" value="商品一覧"><br><br>
		<input type="submit" name="button" value="ログアウト">
	</form>
	<%@include file="footer.jsp" %>
</body>
</html>