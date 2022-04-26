<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>인증된 페이지</h2>
<% String name = (String)session.getAttribute("nameKey"); %>
<%=name  %>님 환영합니다.<br>
<a href="logout.jsp">로그아웃</a>
</body>
</html>