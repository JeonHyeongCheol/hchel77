<%@page import="my.shop.product.ProductBean"%>
<%@page import="my.shop.product.OrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="my.shop.product.OrderManager"/>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>주문 상품 목록</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2 align="center">** 주문 상품 목록 **</h2>
<%@ include file="guest_top.jsp" %>
<br>
<hr>
<div align="center">
<table style="width: 70%; border-collapse:collapse;" >
	<tr style="background-color: silver;">
		<th>주문번호</th><th>상품명</th><th>주문수량</th><th>주문일</th><th>주문상태</th>
	</tr>
	<%
	String id = (String)session.getAttribute("idKey");
	ArrayList<OrderBean> list = orderMgr.getOrder(id);
	
	if(list.size() == 0) {
		%>
		<tr><td colspan="5">주문 내역이 없습니다</td></tr>
		<%
	} else {
		for(OrderBean o:list) {
			ProductBean product = productMgr.getProduct(o.getProduct_no());
			%>
			<tr style="text-align: center;">
				<td><%=o.getNo() %></td>
				<td><%=product.getName() %></td>
				<td><%=o.getQuantity() %></td>
				<td><%=o.getSdate() %></td>
				<td>
				<%
				switch(Integer.parseInt(o.getState())) {
				case 1: out.println("접수"); break;
				case 2: out.println("입금확인"); break;
				case 3: out.println("배송준비"); break;
				case 4: out.println("배송중"); break;
				case 5: out.println("처리완료"); break;
				default : out.println("준비중"); break;
				}
				%>
				</td>
			</tr>
			<%
		}
	}
	%>
</table>
</div>
<%@ include file="guest_bottom.jsp" %>
</body>
</html>