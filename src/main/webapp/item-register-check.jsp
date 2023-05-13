<%@page import="model.entity.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ItemBean item = (ItemBean)request.getAttribute("item");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録確認画面</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@include file="header.jsp" %>

    <h1>商品登録確認画面</h1>
    <hr>
    <form action="item-register" method="post">
            <h3 class="center">以下の商品を登録してもよろしいでしょうか？</h3>
        <table>
            <tr><th>商品名</th><td><%=item.getItemName() %></td></tr>
            <tr><th>タール</th><td><%=item.getTar()%></td></tr>
            <tr><th>ニコチン</th><td><%=item.getNicotine() %></td></tr>
            <tr><th>入数</th><td><%=item.getVolume() %></td></tr>
            <tr><th>価格</th><td><%=item.getPrice() %></td></tr>
            <tr><th>商品説明</th><td><%=item.getBio() %></td></tr>
        </table>
        <input type="hidden" name="itemName" value=<%=item.getItemName() %>>
        <input type="hidden" name="tar" value=<%=item.getTar()%>>
        <input type="hidden" name="nicotine" value=<%=item.getNicotine()%>>
        <input type="hidden" name="volume" value=<%=item.getVolume()%>>
        <input type="hidden" name="price" value=<%=item.getPrice()%>>
        <input type="hidden" name="bio" value=<%=item.getBio()%>>
        <input type="hidden" name="process" value="register-end">
        <input type="submit" name="button" value="登録">
        <input type="submit" name="button" value="戻る">
    </form>
    <%@include file="footer.jsp" %>
</body>
</html>