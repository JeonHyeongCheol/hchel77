<%@page import="my.shop.product.ProductBean"%>
<%@page import="my.shop.product.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="my.shop.product.CartManager" scope="session"/>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2 align="center">** 장바구니 목록 **</h2>
<%@ include file="guest_top.jsp" %>
<br>
<hr>
<div align="center">
<table style="width: 70%; border-collapse:collapse;" >
	<tr style="background-color: silver;">
		<th>주문상품</th><th>가격(소계)</th><th>주문수량</th><th>수정/삭제</th><th>조회</th>
	</tr>
	<%
	int totalPrice = 0;
	Hashtable hCart = cartMgr.getCartList();
	if(hCart.size() == 0) {
	%>
	<tr><td colspan="5">장바구니 내역이 없습니다</td></tr>
	<%
	} else {
		Enumeration enu = hCart.keys(); // hashtable에서 값을 꺼낼 때 Enumeration 사용.
		while(enu.hasMoreElements()) {
			OrderBean order = (OrderBean)hCart.get(enu.nextElement());
			ProductBean product = productMgr.getProduct(order.getProduct_no());
			
			int price = Integer.parseInt(product.getPrice());
			int quantity = Integer.parseInt(order.getQuantity());
			int subTotal = price * quantity;
			totalPrice += subTotal;
	%>
	<form action="cartproc.jsp" method="get">
		<input type="hidden" name="flag">
		<input type="hidden" name="product_no" value="<%=product.getNo() %>">
	
	<tr align="center">
		<td><%=product.getName() %></td>
		<td><%=subTotal %></td>
		<td><input type="text" name="quantity" size="5" style="text-align: center" value="<%=quantity %>"></td>
		<td><input type="button" value="수정" style="background-color: green; color: white;" onclick="cartUpdate(this.form)">/
			<input type="button" value="삭제" style="background-color: green; color: white;" onclick="cartDelete(this.form)">
		</td>
		<td>상세보기</td>
	</tr>
	</form>
	<%
		}
	%>
	<tr>
		<td colspan="5" align="center">
		<br>
		<b>총 결제금액 : <%=totalPrice %></b>
		&nbsp;&nbsp;<a href="orderproc.jsp">주문하기</a>
		</td>
	</tr>
	<%
	}
	%>	
</table>
</div>
<%@ include file="guest_bottom.jsp" %>


</body>
</html>