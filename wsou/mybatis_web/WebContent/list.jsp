<%@page import="pack.business.DataDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="processData" class="pack.business.ProcessData"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 상품 자료(MyBatis) *
<a href="ins.html">상품추가</a><br>
<b>코드를 클릭하면 삭제, 품명을 클릭하면 수정하기</b>
<table border="1">
	<tr>
		<th>code</th><th>sang</th><th>su</th><th>dan</th>
	</tr>
	<% ArrayList<DataDto> list = (ArrayList)processData.readDataAll(); %>
	<c:forEach var="s" items="<%=list %>">
	<tr>
		<td><a href="delete.jsp?code=${s.code}">${s.code}</a></td>
		<td><a href="update.jsp?code=${s.sang}">${s.sang}</a></td>
		<td>${s.su}</td>
		<td>${s.dan}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>