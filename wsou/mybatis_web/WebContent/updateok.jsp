<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="pack.business.DataForm" />
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="processData" class="pack.business.ProcessData"/>
<%
processData.updateData(bean);
response.sendRedirect("list.jsp");
%>