<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
session = request.getSession(false);
if(session == null ||session.getAttribute("userid") == null) {
	//response.sendRedirect("login.jsp");
	%>
	<script>
	alert("로그인 후 목록보기 가능!");
	location.href = 'login.jsp';
	</script>
	<%
	return;
}
%>