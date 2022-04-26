<%@page import="my.shop.product.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager"/>
<% 
String no = request.getParameter("no");

ProductBean bean= productMgr.getProduct(no);
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세보기</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2 align="center">* 상품 상세보기 *</h2>
<%@ include file="admin_top.jsp" %>
<br>
<table style="width:100%">
	<tr>
		<td width="20%">
			<img src="../data/<%=bean.getImage() %>" width="150">
		</td>

		<td style="width: 50%">
		 <table style="width: 100%">
		 	<tr>
		 		<td>번호 : </td>
		 		<td><%=bean.getNo() %></td>
		 	</tr>
		 	<tr>
		 		<td>상품명 : </td>
		 		<td><%=bean.getName() %></td>
		 	</tr>
		 	<tr>
		 		<td>가격 : </td>
		 		<td><%=bean.getPrice() %></td>
		 	</tr>
		 	<tr>
		 		<td>등록일 : </td>
		 		<td><%=bean.getSdate() %></td>
		 	</tr>
		 	<tr>
		 		<td>재고량 : </td>
		 		<td><%=bean.getStock()%></td>
		 	</tr>
		 </table>	
		</td>
		<td style="width: 30%;">
			<b>* 상품설명 *</b><br>
			<%=bean.getDetail() %>
		</td>
	</tr>
	
	<tr align="center">
		<td colspan="3">
		<a href="javascript:productUpdate('<%=bean.getNo()%>')">수정하기</a>
		<a href="javascript:productDelete('<%=bean.getNo()%>')">삭제하기</a>
		</td>
	</tr>
</table>
<br>
<%@ include file="admin_bottom.jsp" %>
<form action="productupdate.jsp" name="updateFrm" method="post">
<input type="hidden" name="no">
</form>

<form action="productproc.jsp?flag=delete" name="delFrm" method="post">
<input type="hidden" name="no">
</form>
</body>
</html>