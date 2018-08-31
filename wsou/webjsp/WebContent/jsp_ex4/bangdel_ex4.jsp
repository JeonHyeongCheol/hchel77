<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% String code = request.getParameter("code"); %>
<jsp:useBean id="bang" class="beanpack.Bang_ex4"/>
<%
if(bang.delData(code)){
	response.sendRedirect("bang.jsp");
} else {
	response.sendRedirect("bnagfail_ex4.html");
}
%>
