<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
환영
<%
if(request.getParameter("name") == null){
	%>
	<jsp:forward page="el1.html"></jsp:forward>
	<%
}
%>
<br>결과 출력 : <br>
jsp tag : <%= request.getParameter("name") %><br>
el : ${param.name} 
<!-- 이것이 EL ! 위에 라인에 출력과 같은 것. 쓸 때는 param이라고 쓰면 됨. -->
</body>
</html>