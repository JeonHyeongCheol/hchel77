<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="pack.business.DataForm"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="processData" class="pack.business.ProcessData" />
<%
processData.insertData(bean); // bean 값을 들고 넘어감.
response.sendRedirect("list.jsp"); // insert 후 list.jsp로 돌아감.
%>
