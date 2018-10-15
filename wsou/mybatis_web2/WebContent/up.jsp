<%@page import="pack.business.DataDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="processDao" class="pack.business.ProcessDao"/>
<%
	String id = request.getParameter("id");
System.out.println(id);
DataDto dto = processDao.selectDataPart(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>* 회원 수정 *</h2>
<form action="up_ok.jsp" method="post">
아이디 : <%= dto.getId() %>
<input type="hidden" name="id" value="<%= id %>"><br>
이름 : <input type="text" name="name" value="<%= dto.getName() %>"><br>
비밀번호 : <input type="text" name="passwd"><br>
등록일 : <%= dto.getReg_date() %><br>
<br>
<input type="submit" value="등록">
</form>
</body>
</html>