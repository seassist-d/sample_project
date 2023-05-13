<%@page import="model.entity.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ItemBean item = (ItemBean)request.getAttribute("item");
String itemNameErrorMsg = "";
if((String)request.getAttribute("itemNameErrorMsg") != null){
itemNameErrorMsg = (String)request.getAttribute("itemNameErrorMsg");
}
String tarErrorMsg = "";
if((String)request.getAttribute("tarErrorMsg") != null){
    tarErrorMsg = (String)request.getAttribute("tarErrorMsg");
}
String nicotineErrorMsg = "";
if((String)request.getAttribute("nicotineErrorMsg") != null){
nicotineErrorMsg = (String)request.getAttribute("nicotineErrorMsg");
}
String volumeErrorMsg = "";
if((String)request.getAttribute("volumeErrorMsg") != null){
volumeErrorMsg = (String)request.getAttribute("volumeErrorMsg");
}
String priceErrorMsg = "";
if((String)request.getAttribute("priceErrorMsg") != null){
priceErrorMsg = (String)request.getAttribute("priceErrorMsg");
}

String seachCheck = (String) request.getAttribute("seachCheck");
String seachWord = (String) request.getAttribute("seachWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@include file="header.jsp" %>

	<h1>商品編集</h1>
	<hr>
	<form action="item-edit" method="post">
		<table>
			<tr>
				<th>商品名</th>
				<td>
				<%if (!"".equals(item.getItemName())){ %> 
					<input type="text" name="itemName" value=<%=item.getItemName() %>>
				<%} else { %>
					<input type="text" name="itemName">
				<%} %>
				<div class="errCenter"><%=itemNameErrorMsg %></div>
				</td>
			</tr>
			<tr>
			<th>タール</th>
				<td>
				<%if (item.getTar() <= 0){ %>
					<input type="text" name="tar" value="">
				<%} else { %>
					<input type="text" name="tar" value=<%=item.getTar() %>>
				<%} %>
				<div class="errCenter"><%=tarErrorMsg %></div>
				</td>
			</tr>
			<tr>
			<th>ニコチン</th>
				<td>
				<%if (item.getNicotine() <= 0){ %>
					<input type="text" name="nicotine" value="">
				<%} else { %>
					<input type="text" name="nicotine" value=<%=item.getNicotine() %>>
				<%} %>
				<div class="errCenter"><%=nicotineErrorMsg %></div>
				</td>
			</tr>
			<tr>
			<th>入数</th>
				<td>
				<%if (item.getVolume() <= 0){ %>
					<input type="text" name="volume" value="">
				<%} else { %>
					<input type="text" name="volume" value=<%=item.getVolume() %>>
				<%} %>
				<div class="errCenter"><%=volumeErrorMsg %></div>
				</td>
			</tr>
			<tr>
			<th>価格</th>
				<td>
				
				<%if (item.getPrice() <= 0){ %>
					<input type="text" name="price" value="">
				<%} else { %>
					<input type="text" name="price" value=<%=item.getPrice() %>>
				<%} %>
				<div class="errCenter"><%=priceErrorMsg %></div>
				</td>
			</tr>
			<tr>
			<th>商品説明</th>
				<td>
				
				<%if (!"".equals(item.getBio())){ %>
					<textarea name="bio" ><%=item.getBio() %></textarea>
				<%} else { %>
					<textarea name="bio"></textarea>
				<%} %>
				</td>
			</tr>
			
		</table>
		<input type="hidden" name="itemCode" value=<%=item.getItemCode() %>>
		<input type="hidden" name="pageCheck" value="edit">
		<input type="submit" name="button" value="編集">
		<input type="submit" name="button" value="戻る">
		<input type="hidden" name="seachWord" value="<%=seachWord %>">
		<input type="hidden" name="seachCheck" value="<%=seachCheck%>">
	</form>
	<%@include file="footerDown.jsp" %>
</body>
</html>