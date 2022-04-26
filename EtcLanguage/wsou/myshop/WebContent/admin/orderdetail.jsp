<%@page import="my.shop.product.OrderBean"%>
<%@page import="my.shop.product.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="my.shop.product.OrderManager"/>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager"/>

<% 

OrderBean order = orderMgr.getOrderDetail(request.getParameter("no"));
ProductBean product = productMgr.getProduct(order.getProduct_no());

%>

<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세보기</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2 align="center">** 주문 상세보기 **</h2>
<%@ include file="admin_top.jsp" %>
<br>
<hr>
<div align="center">
<form action="orderproc_admin.jsp" name="orderDetailFrm" method="post">
<table style="width: 70%; border-collapse:collapse;" >
	<tr style="background-color: silver;">
		<td>고객 아이디 : <%=order.getId() %></td>
		<td>주 문 번 호 : <%=order.getNo() %></td>
		<td>상 품 번 호 : <%=product.getNo() %></td>
		<td>상&nbsp;&nbsp;품&nbsp;&nbsp;명 : <%=product.getName() %></td>
	</tr>
	<tr style="background-color: silver;">
		<td>상 품 가 격 : <%=product.getPrice() %></td>
		<td>주 문 수 량 : <%=order.getQuantity() %></td>
		<td>재&nbsp;&nbsp;고&nbsp;&nbsp;수 : <%=product.getStock() %></td>
		<td>주&nbsp;&nbsp;문&nbsp;&nbsp;일 : <%=product.getSdate() %></td>
	</tr>
	<tr>
		<td colspan="4" style="text-align: right;">총액 :
		<%=Integer.parseInt(order.getQuantity()) * Integer.parseInt(product.getPrice())%>
		</td>
	</tr>
	<tr>
		<td colspan="4" style="text-align: center;">
			주문상태 : 
			<select name="state">
				<option value="1">접수</option>
				<option value="2">입금확인</option>
				<option value="3">배송준비</option>
				<option value="4">배송중</option>
				<option value="5">배송완료</option>
			</select>
			<script type="text/javascript">
			document.orderDetailFrm.state.value = <%=order.getState()%>
			</script>
		</td>
	</tr>
	<tr>
		<td colspan="4" style="text-align: center;">
			<input type="button" value="수 정" onclick="orderUpdate(this.form)">
			<input type="button" value="삭 제">
			<input type="hidden" name="no" value="<%=order.getNo()%>">
			<input type="hidden" name="flag">
		</td>
	</tr>
</table>
</form>
</div>
<%@ include file="admin_bottom.jsp" %>
</body>
</html>