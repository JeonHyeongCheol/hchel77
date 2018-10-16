<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 취미 선택 결과 1 : jsp *
<%
	ArrayList<String> list = (ArrayList)request.getAttribute("data");
	for(String i : list) { // 향상된 for문
		 out.println(i);
	}
%>
<hr>
* 취미 선택 결과 1 : jstl *
<c:forEach var="i" items="${data}"> <!-- items 에는 속성 값 사용. <%//=list %> 이렇게 사용하여도 됨.--> 
	${i}
</c:forEach>
</body>
</html>