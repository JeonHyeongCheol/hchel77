<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="logincheck.jsp" %>
<%
//<a href="insa.do">요청1</a><br> <!-- File명을 요청명으로 쓸 것임. Spring에서는 File명이 요청명이 됨. -->
//<a href="chongmu.do">요청2</a>
%>
* 사용자 목록 *
<a href="insert.jsp">사용자 추가</a>
<hr>
<table border="1">
	<tr>
		<th>ID</th><th>Name</th><th>Email</th>
	</tr>
	<c:forEach var="u" items="${list}">
		<tr>
			<td>${u.userid}</td>
			<td><a href="view.do?userid=${u.userid}">${u.name}</a></td> <!-- 확장자 .do를 써야지 Servlet을 만남. -->
			<td>${u.email}</td>
		</tr>	
	</c:forEach>
</table>
<hr>
<a href="logout.do">로그아웃</a> <!-- 확장자 .do를 써야지 Servlet을 만남. -->
</body>
</html>