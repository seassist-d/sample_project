<%@page import="model.entity.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   ItemBean item = (ItemBean) request.getAttribute("item");
   String imagePath = (String) request.getAttribute("imagePath");
   String seachCheck = (String) request.getAttribute("seachCheck");
   String seachWord = (String) request.getAttribute("seachWord");

   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>確認画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

<h1>確認画面</h1>
<hr>

<h3 class="center"><%=item.getItemName()%>の画像の編集を確定しますか？</h3>
<div class="pic">
<img src="<%=imagePath%>" alt="商品画像が設定されていません" width="300" height="300"><br>
</div>
<form action="item-edit-pic" method="get" enctype="multipart/form-data">
	<input type="submit" name="button" value="確定">
	<input type="submit" name="button" value="キャンセル">
	<input type="hidden" name="itemCode" value="<%=item.getItemCode() %>">
	<input type="hidden" name="imagePath" value="<%=imagePath %>">
	<input type="hidden" name="seachWord" value="<%=seachWord %>">
	<input type="hidden" name="seachCheck" value="<%=seachCheck%>">
	
</form>
<%@include file="footer.jsp" %>
</body>
</html>