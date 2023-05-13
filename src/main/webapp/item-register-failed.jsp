<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録失敗画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

	<h1>商品の登録に失敗しました</h1>
	<hr>
	
	<h3 class="center">お手数ですが、管理会社までお問い合わせください。</h3>
	<form action="menu" method="get">
		<input type="submit" name="button" value="メニュー画面">
	</form>
	<%@include file="footer.jsp" %>
</body>
</html>