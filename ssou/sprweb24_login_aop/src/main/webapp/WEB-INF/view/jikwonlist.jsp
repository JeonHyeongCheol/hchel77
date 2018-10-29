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
** 직원 자료 ** </p>
<%

if(session.getAttribute("name") != null)
	out.print("<a href='logout'>로그아웃</a>");

%>
<table border="1">
	<tr>
		<th>번호</th><th>직원명</th><th>직급</th><th>부서명</th>
	</tr>
	<c:forEach var="j" items="${list}">
		<tr>
			<td>${j.jikwon_no}</td>
			<td>${j.jikwon_name}</td>
			<td>${j.jikwon_jik}</td>
			<td>${j.buser_name}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>