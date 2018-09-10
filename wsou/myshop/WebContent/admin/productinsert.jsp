<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>상품추가</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2 align="center">* 상품 등록 *</h2>
<%@ include file="admin_top.jsp" %>
<br>
<form action="productproc.jsp?flag=insert" enctype="multipart/form-data" method="post">
<%-- multipart 사용 --%>
<%-- 컨트롤러 방식으로 할 것이면 productproc에서 insert, delete, update를 다 처리 할 것임. --%>
<%-- flag 값을 통해 가능. --%>
<table style="width:100%">
	<tr>
		<td>상품명</td>
		<td><input type="text" name="name"></td>
	</tr>
	<tr>
		<td>가 격</td>
		<td><input type="text" name="price"></td>
	</tr>
	<tr>
		<td>설 명</td>
		<td><input type="text" name="detail"></td>
	</tr>
	<tr>
		<td>재고량</td>
		<td><input type="text" name="stock"></td>
	</tr>
	<tr>
		<td>이미지</td>
		<td><input type="file" name="image"></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
		<br>
		<input type="submit" value="상품등록">
		<input type="reset" value="새로입력">
	</tr>
</table>
<br>
</form>

<%@ include file="admin_bottom.jsp" %>
</body>
</html>