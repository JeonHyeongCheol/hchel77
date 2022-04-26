<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>XSS sample</h2>
<%
request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");

// XSS 방지용 추가 코딩. "<" ==> &lt;
if(name != null) {
	name = name.replaceAll("<", "&lt;"); // 꺽새로 인정 안하고 데이터로 바꿔버림.
	name = name.replaceAll(">", "&gt;");
} else {
	return;
}
%>

이름은 <%=name %>
</body>
</html>