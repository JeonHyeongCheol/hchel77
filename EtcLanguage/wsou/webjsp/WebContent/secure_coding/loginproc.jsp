<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="secure_pack.LoginDao"/>

<%
request.setCharacterEncoding("utf-8");
String no = request.getParameter("no");
String name = request.getParameter("name");

boolean check = dao.loginCheck(no, name);

if(check) {
	session.setAttribute("nameKey", name);
	response.sendRedirect("logsuccess.jsp");
} else {
	response.sendRedirect("logfail.jsp");
}
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>