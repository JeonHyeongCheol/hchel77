<%@page import="my.shop.product.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager"/>
<%
ProductBean bean = productMgr.getProduct(request.getParameter("no"));
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<table style="width: 100%">
	<tr align="center">
		<td colspan="2">
		<h2>** 상품 수정 **</h2>
		</td>
	</tr>
</table>
<%@ include file="admin_top.jsp" %>
<form action="productproc.jsp?flag=update" enctype="multipart/form-data" method="post">
<br>
<table style="width: 100%">
	<tr>
		<td>상품명</td>
		<td><input type="text" name="name" value="<%=bean.getName()%>">
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="price" value="<%=bean.getPrice()%>">
	</tr> 
	<tr>
		<td>설명</td>
		<td><input type="text" name="detail" value="<%=bean.getDetail()%>">
	</tr>
	<tr>
		<td>재고량</td>
		<td><input type="text" name="stock" value="<%=bean.getStock()%>">
	</tr>
	<tr>
		<td>이미지</td>
		<td><input type="file" name="image" value="<%=bean.getImage()%>"></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<br>
			<input type="hidden" name="no" value="<%=bean.getNo()%>">
			<input type="submit" value="상품수정">
			<input type="button" value="수정취소" onclick="history.back()">
		</td>
	</tr>
</table>
</form>
<br>
<%@ include file="admin_bottom.jsp" %>
</body>
</html>