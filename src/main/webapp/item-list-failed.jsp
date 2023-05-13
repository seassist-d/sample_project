<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報取得失敗画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

	<h1>商品情報の取得ができませんでした。</h1>
	<p>お手数ですが、管理会社までお問い合わせください。</p>
	<form action="menu" method="get">
		<input type="submit" name="button" value="メニュー画面"><br><br>
	</form>
	<%@include file="footer.jsp" %>
</body>
</html>