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
<h2>** ${name} 고객 자료 **</h2> 
<br>
<%
if(session.getAttribute("name") != null)
	out.print("<a href='logout'>로그아웃</a></p>");
%>
<table border="1">
	<tr>
		<th>직원명</th><th>부서</th><th>직급</th><th>부서전화</th><th>성별</th>
	</tr>
		<tr>
			<td>${jikwon.jikwon_name}</td>
			<td>${jikwon.buser_name}</td>
			<td>${jikwon.jikwon_jik}</td>
			<td>${jikwon.buser_tel}</td>
			<td>${jikwon.jikwon_gen}</td>
</table>
</body>
</html>