<%@page import="model.entity.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
ItemBean item = (ItemBean) request.getAttribute("item");
int count = (int) request.getAttribute("count");
String seachCheck = (String) request.getAttribute("seachCheck");
String seachWord = (String) request.getAttribute("seachWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品削除結果画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

<h1>商品削除</h1><br>
<hr>
	<%
	if(count != 0){
	%>
	
		<div class="center"><%=item.getItemName() %>を削除しました。</div>
	
	<%
	}else{
	%>
		<div class="center"><%=item.getItemName() %>を削除出来ませんでした。<br>
		お手数ですが、管理会社へお問い合わせください。</div>
	<%
	}
	%>
	
		<form action="item-list" method="post">
			<input type="submit" value="商品一覧">
		
		</form>
<%@include file="footer.jsp" %>
</body>
</html>