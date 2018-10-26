<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>** 회원 정보(@MVC - Mybatis(CRUD)) **</h2></p>
<a href="insert">회원추가</a>
<table border="1">
	<tr>
		<th>번호</th><th>이름</th><th>주소</th><th>변경</th>
	</tr>
	<c:forEach var="m" items="${list}">
	<tr>
		<td>${m.num}</td>
		<td>${m.name}</td>
		<td>${m.addr}</td>
		<td>
		<a href="update?num=${m.num}">변경</a> <!-- uri에서 update/값/값  (/)를 사용해 값을 달아서 갈 수 있음. -->
		<a href="delete?num=${m.num}">삭제</a>
		</td>	
	</tr>
	</c:forEach>
</table>
</body>
</html>