<%@page import="my.shop.product.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="my.shop.product.CartManager" scope="session"/>
<jsp:useBean id="orderMgr" class="my.shop.product.OrderManager" scope="session"/>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager" scope="session"/>

<%
Hashtable hCart = cartMgr.getCartList();

Enumeration enu = hCart.keys();
if(hCart.size() == 0) {
%>
	<script>
	alert("주문 내역이 없습니다");
	location.href = "orderlist.jsp";
	</script>
<% } else { 
	while(enu.hasMoreElements()) {
		OrderBean order = (OrderBean)hCart.get(enu.nextElement());
		orderMgr.insertOrder(order); // 주문 상품 DB에 저장.
		productMgr.reduceProduct(order); // 주문상품만큼 상품재고 빼기.
		cartMgr.deleteCart(order); // 장바구니에 있던 상품은 주문하였으니 삭제.
	}
%>
	<script>
	alert("주문 완료");
	location.href = "orderlist.jsp";
	</script>

<%
}
%>