<%@page import="model.entity.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ItemBean item = (ItemBean) request.getAttribute("item");
String err = (String)request.getAttribute("err");
String seachCheck = (String) request.getAttribute("seachCheck");
String seachWord = (String) request.getAttribute("seachWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品画像編集画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

<h1>画像を選択してください</h1>
<hr>
<form action="item-edit-pic" method="post" enctype="multipart/form-data">
	<input type="file" name="pic" accept="image/*"><br>
	<%if (!"".equals(err)) {%>
		<span class="errCenter"><%=err %></span>
	<%} %>
	<br>
	<input type="submit" name="button" value="登録">
	<input type="submit" name="button" value="戻る">
	<input type="hidden" name="itemCode" value="<%=item.getItemCode()%>">
	<input type="hidden" name="seachWord" value="<%=seachWord %>">
	<input type="hidden" name="seachCheck" value="<%=seachCheck%>">
</form>
<%@include file="footer.jsp" %>
</body>
</html>