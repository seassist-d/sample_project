<%@page import="model.entity.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ItemBean item = (ItemBean) request.getAttribute("item");
ItemBean newItem = (ItemBean) request.getAttribute("newItem");
String editErr = (String) request.getAttribute("editErr");
String seachCheck = (String) request.getAttribute("seachCheck");
String seachWord = (String) request.getAttribute("seachWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集結果画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

	<%
	if (!"".equals(editErr)) {
	%>
	<h1><%=item.getItemName()%>を<%=editErr%></h1>
	お手数ですが、管理会社へお問い合わせください。
	<%
	} else {
	%>
	<h1><%=item.getItemName()%>を編集しました。
	</h1>
	<table>
		<tr>
			<th></th>
			<th>Before</th>
			<th>After</th>
		</tr>
		<tr>
			<th>商品名</th>
			<td><%=item.getItemName()%></td>
			<td><%=newItem.getItemName()%></td>
		</tr>
		<tr>
			<th>タール</th>
			<td><%=item.getTar()%> mg</td>
			<td><%=newItem.getTar()%> mg</td>
		</tr>
		<tr>
			<th>ニコチン</th>
			<td><%=item.getNicotine()%> mg</td>
			<td><%=newItem.getNicotine()%> mg</td>
		</tr>
		<tr>
			<th>入数</th>
			<td><%=item.getVolume()%> 本/箱</td>
			<td><%=newItem.getVolume()%> 本/箱</td>
		</tr>
		<tr>
			<th>価格</th>
			<td><%=item.getPrice()%> 円</td>
			<td><%=newItem.getPrice()%> 円</td>
		</tr>
		<tr>
			<th>商品説明</th>
			<td><%=item.getBio()%></td>
			<td><%=newItem.getBio()%></td>
		</tr>
	</table>
	<%
	}
	%>
	<hr>
	<form action="item-detail" method="get">
		<input type="submit" value="詳細画面">
		<input type="hidden"
			name="itemCode" value=<%=item.getItemCode()%>>
			<input type="hidden" name="seachWord" value="<%=seachWord %>">
	<input type="hidden" name="seachCheck" value="<%=seachCheck%>">
			
	</form>
	<%@include file="footerDown.jsp" %>
</body>
</html>