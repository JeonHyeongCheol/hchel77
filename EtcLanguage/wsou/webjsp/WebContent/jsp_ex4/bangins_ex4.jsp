<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bang" class="beanpack.Bang_ex4"/>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dto" class="beanpack.BangDto_ex4"/>
<jsp:setProperty property="*" name="form"/>

<%
bang.insertData(dto);

response.sendRedirect("bang.jsp");
%>
