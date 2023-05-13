<%@page import="model.entity.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
String itemNameErrorMsg = "";
if ((String) request.getAttribute("itemNameErrorMsg") != null) {
itemNameErrorMsg = (String) request.getAttribute("itemNameErrorMsg");
}
String tarErrorMsg = "";
if ((String) request.getAttribute("tarErrorMsg") != null) {
tarErrorMsg = (String) request.getAttribute("tarErrorMsg");
}
String nicotineErrorMsg = "";
if ((String) request.getAttribute("nicotineErrorMsg") != null) {
nicotineErrorMsg = (String) request.getAttribute("nicotineErrorMsg");
}
String volumeErrorMsg = "";
if ((String) request.getAttribute("volumeErrorMsg") != null) {
volumeErrorMsg = (String) request.getAttribute("volumeErrorMsg");
}
String priceErrorMsg = "";
if ((String) request.getAttribute("priceErrorMsg") != null) {
priceErrorMsg = (String) request.getAttribute("priceErrorMsg");
}
ItemBean item = (ItemBean) request.getAttribute("item");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@include file="header.jsp" %>

<h1>新規登録画面</h1>
<hr>
<form action="item-register" method="post">
<table>
<%
if ((ItemBean) request.getAttribute("item") != null) {
%>
<tr>
<th>商品名</th>

<td>
<input type="text" name="itemName"value="<%=item.getItemName()%>" placeholder="例：マイルドセブン"></input><br>
<div class="errCenter"><%=itemNameErrorMsg%></div>
</td>
</tr>
<tr>
<th>タール</th>
<td>
<%
if(item.getTar() <= 0){
%>
<input type="text" name="tar" value="" placeholder="例：10"></input><br><div class="errCenter"><%=tarErrorMsg%></div>
<%
}else {
%>
<input type="text" name="tar" value="<%=item.getTar() %>" placeholder="例：10"></input><br><div class="errCenter"><%=tarErrorMsg%></div>
<%
}
%>
</td>
</tr>
<tr>
<th>ニコチン</th>
<td>
<%
if(item.getNicotine() <= 0){
%>
<input type="text" name="nicotine"value="" placeholder="例：0.1"></input><br><div class="errCenter"><%=nicotineErrorMsg%></div>
<%
}else {
%>
<input type="text" name="nicotine"value="<%=item.getNicotine()%>" placeholder="例：0.1"></input><br><div class="errCenter"><%=nicotineErrorMsg%></div>
<%
}
%>
</td>
</tr>
<tr>
<th>入数</th>
<td>
<%
if(item.getVolume() <= 0){
%>
<input type="text" name="volume" value="" placeholder="例：20"></input><br><div class="errCenter"><%=volumeErrorMsg%></div>
<%
}else {
%>
<input type="text" name="volume" value="<%=item.getVolume()%>" placeholder="例：20"></input><br><div class="errCenter"><%=volumeErrorMsg%></div>
<%
}
%>
</td>
</tr>
<tr>
<th>価格</th>
<td>
<%
if(item.getPrice() <= 0){
%>
<input type="text" name="price"value="" placeholder="例：600"></input><br><div class="errCenter"><%=priceErrorMsg%></div>
<%
}else {
%>
<input type="text" name="price"value="<%=item.getPrice()%>" placeholder="例：600"></input><br><div class="errCenter"><%=priceErrorMsg%></div>
<%
}
%>

</td>
</tr>
<tr>
<th>商品説明</th>
<td><textarea name="bio" cols="40" rows="10"placeholder="例：コクがある"><%=item.getBio()%></textarea></td>
</tr>
<tr>
<td colspan="2">
<input type="hidden" name="process" value="input-end"></input>
<input type="submit" name="button" value="登録"></input>
<input type="submit" name="button" value="クリア"></input>
</td>
</tr>
<%
} else {
%>
<tr>
<th>商品名</th>
<td><input type="text" name="itemName" placeholder="例：マイルドセブン"></input></td>
</tr>
<tr>
<th>タール</th>
<td><input type="text" name="tar" placeholder="例：10"></input></td>
</tr>
<tr>
<th>ニコチン</th>
<td><input type="text" name="nicotine" placeholder="例：0.1"></input></td>
</tr>
<tr>
<th>入数</th>
<td><input type="text" name="volume" placeholder="例：20"></input></td>
</tr>
<tr>
<th>価格</th>
<td><input type="text" name="price" placeholder="例：600"></input></td>
</tr>
<tr>
<th>商品説明</th>
<td><textarea name="bio" cols="40" rows="10"
placeholder="例：コクがある"></textarea></td>
</tr>
<tr>
<td colspan="2">
<input type="hidden" name="process" value="input-end"></input>
<input type="submit" name="button" value="登録"></input><input type="submit" name="button" value="クリア"></input></td>
</tr>
<%
}
%>
</table>

</form>
<%@include file="footerDown.jsp" %>
</body>
</html>