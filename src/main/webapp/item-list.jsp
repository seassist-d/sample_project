<%@page import="model.entity.ItemBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
@SuppressWarnings("unchecked")
List<ItemBean> itemList = (List<ItemBean>) request.getAttribute("itemList");
List<ItemBean> seachItemList = (List<ItemBean>) request.getAttribute("seachItemList");
String seachCheck = (String) request.getAttribute("seachCheck");
String seachWord = (String) request.getAttribute("seachWord");
String notListMsg = (String)request.getAttribute("notListMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

	<h1>商品一覧</h1>
	<hr>
	
	<%if (!"".equals(notListMsg)) { %>
	<h3 class="center"><%=notListMsg %></h3>
	<%} else {%>
	
	<%if ("".equals(seachCheck)){ %>

	<%
	for (ItemBean item : itemList) {
	%>
	<div class="pic">
		<img src="<%=item.getPic()%>" alt="商品画像が設定されていません" width="300" height="300">
	</div>
		<table>
		<tr>
			<th>商品名</th>
			<td><%=item.getItemName()%></td>
		</tr>
		<tr>
			<th>価格</th>
			<td><%=item.getPrice()%> 円</td>
		</tr>
		</table><br>
	<form action="item-detail" method="get">
		<input type="submit" value="詳細">
		<input type="hidden" name="itemCode" value="<%=item.getItemCode() %>">
	</form><br><br>
	<%
	}
	%>
	<%} else {%>
		<%
	for (ItemBean item : seachItemList) {
	%>
	<div class="pic">
	<img src="<%=item.getPic()%>" alt="商品画像が設定されていません" width="300" height="300">
	</div>
		<table>
		
		<tr>
			<th>商品名</th>
			<td><%=item.getItemName()%></td>
		</tr>
		<tr>
			<th>価格</th>
			<td><%=item.getPrice()%> 円</td>
		</tr>
		</table><br>
	<form action="item-detail" method="get">
		<input type="submit" value="詳細">
		<input type="hidden" name="itemCode" value="<%=item.getItemCode() %>">
		<input type="hidden" name="seachWord" value="<%=seachWord %>">
		<input type="hidden" name="seachCheck" value="<%=seachCheck%>">
	</form><br><br>
	<%} %>
		<%} %>
	<%} %>
		<%@include file="footerDown.jsp" %>
</body>
</html>