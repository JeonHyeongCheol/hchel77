<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="my.shop.member.MemberManager"/>

<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String passwd = request.getParameter("passwd");

boolean b = memberMgr.loginCheck(id, passwd);

if(b) {
	session.setAttribute("idKey", id);
	response.sendRedirect("login.jsp");
} else {
	response.sendRedirect("loginfail.html");
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