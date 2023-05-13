<%@page import="model.entity.ItemBean"%>
<%@page import="java.util.List"%>
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
<title>商品詳細</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

	<h1>商品詳細</h1>
	<hr><br>
	<div class="pic">
	<img src="<%=item.getPic()%>" alt="商品画像が設定されていません" width="200" height="200">
	</div>
	<table>
		<tr>
			<th>商品名</th>
			<td><%=item.getItemName() %></td>
		</tr>
		<tr>
			<th>タール</th>
			<td><%=item.getTar() %>mg</td>
		</tr>
		<tr>
			<th>ニコチン</th>
			<td><%=item.getNicotine() %>mg</td>
		</tr>
		<tr>
			<th>入数</th>
			<td><%=item.getVolume() %>本/箱</td>
		</tr>
		<tr>
			<th>価格</th>
			<td><%=item.getPrice() %>円</td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><%=item.getBio() %></td>
		</tr>
		
	</table>
	<form action="item-detail" method="get">
		<input type="submit" name="button" value="編集">
		<input type="submit" name="button" value="画像編集">
		<input type="submit" name="button" value="削除">
		<input type="submit" name="button" value="戻る">
		<input type="hidden" name="itemCode" value="<%=item.getItemCode() %>">
		<input type="hidden" name="pageCheck" value="detail">
		<input type="hidden" name="seachWord" value="<%=seachWord %>">
		<input type="hidden" name="seachCheck" value="<%=seachCheck%>">
		
	</form>
	<%@include file="footerDown.jsp" %>
</body>
</html>