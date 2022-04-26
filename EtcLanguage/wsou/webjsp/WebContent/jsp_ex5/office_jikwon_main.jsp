<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(session.getAttribute("jikwon_no") == null) {%>
			<%@ include file="office_jikwon_first.jsp" %>	
		<% } else { %>
			<%@ include file="office_jikwon_wait.jsp" %>
		<%} %>
</body>
</html>