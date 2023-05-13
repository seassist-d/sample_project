<%@page import="model.entity.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
ItemBean item = (ItemBean) request.getAttribute("item");
String seachCheck = (String) request.getAttribute("seachCheck");
String seachWord = (String) request.getAttribute("seachWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品削除画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

<h1>商品削除確認画面</h1>
<hr>
<h4 class="center"><%=item.getItemName() %>を削除してもよろしいですか？</h4><br>

<form action="item-delete" method="post">
	<input type="submit" name="button" value="削除">
	<input type="submit" name="button" value="キャンセル">
	<input type="hidden" name="itemCode" value="<%=item.getItemCode()%>">
	<input type="hidden" name="seachWord" value="<%=seachWord %>">
	<input type="hidden" name="seachCheck" value="<%=seachCheck%>">
	
</form>
	<%@include file="footer.jsp" %>

</body>
</html>