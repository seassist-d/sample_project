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
<title>商品編集確認画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

	<h1>この商品の編集を確定しますか？</h1>
	<hr>
	<table>

		<tr>
			<th>商品名</th>
			<td><%=item.getItemName()%></td>
		</tr>
		<tr>
			<th>タール</th>
			<td><%=item.getTar()%> mg</td>
		</tr>
		<tr>
			<th>ニコチン</th>
			<td><%=item.getNicotine()%> mg</td>
		</tr>
		<tr>
		<th>入数</th>
			<td><%=item.getVolume()%> 本/箱</td>
		</tr>
		<tr>
			<th>価格</th>
			<td><%=item.getPrice()%> 円</td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><%=item.getBio()%></td>
		</tr>
	</table>
	<br>
	<form action="item-edit" method="post">
		<input type="submit" name="button" value="確定"> 
		<input
			type="submit" name="button" value="キャンセル"> 
			<input
			type="hidden" name="itemCode" value=<%=item.getItemCode()%>>
		<input type="hidden" name="itemName" value=<%=item.getItemName()%>>
		<input type="hidden" name="tar" value=<%=item.getTar()%>> 
		<input
			type="hidden" name="nicotine" value=<%=item.getNicotine()%>>
		<input type="hidden" name="volume" value=<%=item.getVolume()%>>
		<input type="hidden" name="price" value=<%=item.getPrice()%>>
		<input type="hidden" name="bio" value=<%=item.getBio()%>>
		<input type="hidden" name="seachWord" value="<%=seachWord %>">
	<input type="hidden" name="seachCheck" value="<%=seachCheck%>">
	</form>
	<%@include file="footer.jsp" %>
</body>
</html>