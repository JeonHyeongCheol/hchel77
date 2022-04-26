<%@page import="my.shop.product.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2 align="center">** 쇼핑용 상품목록 **</h2>
<%@ include file="guest_top.jsp" %>
<br>
<hr>
<div align="center">
<table style="width: 70%; border-collapse:collapse;" >
	<tr>
		<th>상품</th><th>가격</th><th>재고량</th><th>상세보기</th>
	</tr>
	<%
	ArrayList<ProductBean> list = productMgr.getProductAll();
	for(ProductBean p:list) {
	%>
	<tr style="text-align: center; border:1px solid black; border-collapse:collapse;">
		<td style="text-align: center">
			<img src="../data/<%=p.getImage() %>" width="20%" style="float:left; margin: auto;"><p style="float:left; width: 80%"><%=p.getName() %></p>
		</td>
		<td><%=p.getPrice() %></td>
		<td><%=p.getStock() %></td>
		<td><a href="javascript:productDetail('<%=p.getNo()%>')">보기</a></td>	
	</tr>
	<%
	}
	%>
</table>
</div>
<%@ include file="guest_bottom.jsp" %>
<form action="productdetail_g.jsp" name="detailFrm" method="post">
	<input type="hidden" name="no">
</form>
</body>
</html>