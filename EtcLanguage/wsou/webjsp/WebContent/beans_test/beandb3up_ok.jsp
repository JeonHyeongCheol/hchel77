<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="form" class="beanpack.SangpumForm"/>
<jsp:setProperty property="*" name="form"/>
<jsp:useBean id="test" class="beanpack.ConnBeanPoling"/>
<%
if(test.updateData(form)){
	response.sendRedirect("beandb3.jsp");
} else {
	response.sendRedirect("beandb3fail.html");
}

%>