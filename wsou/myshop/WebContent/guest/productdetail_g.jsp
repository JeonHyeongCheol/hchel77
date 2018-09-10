<%@page import="my.shop.product.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager"/>
<%
String no = request.getParameter("no");
ProductBean bean = productMgr.getProduct(no);
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2 align="center">** 상품 상세보기 **</h2>
<%@ include file="guest_top.jsp" %>
<form action="cartproc.jsp" method="post">
<table style="width: 100%">
	<tr>
		<td style="width:20%">
			<img src="../data/<%=bean.getImage() %>" width="150">
		</td>
		<td style="width:50%">
		<table style="width: 50%">
			<tr><td>번 호 : <%=bean.getNo() %></td></tr>
			<tr><td>상 품 : <%=bean.getName() %></td></tr>
			<tr><td>가 격 : <%=bean.getPrice() %></td></tr>
			<tr><td>등 록 : <%=bean.getSdate() %></td></tr>
			<tr><td>재 고 : <%=bean.getStock() %></td></tr>
			<tr>
				<td>
				 주문 수 : <input type="text" name="quantity" value="1" size="5" style="text-align: center">
				</td>
			</tr>
		</table>
		</td>
		<td style="width:30%; vertical-align: top">
			* 상품설명 *<br>
			<%=bean.getDetail() %>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center">
		<br>
		<input type="hidden" name="product_no" value="<%=bean.getNo()%>">
		<input type="submit" value="장바구니에 담기">
		<input type="button" value="이 전 화 면" onclick="history.back()">
	</tr>
</table>
</form>
<%@ include file="guest_bottom.jsp" %>
</body>
</html>