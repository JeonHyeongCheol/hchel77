<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% String code = request.getParameter("code"); %>
<jsp:useBean id="test" class="beanpack.ConnBeanPoling"/>
<%
if(test.delData(code)){
	response.sendRedirect("beandb3.jsp");
} else {
	response.sendRedirect("beandb3fail.html");
}
%>
