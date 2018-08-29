<%@page import="pack.Session_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");
int su = Integer.parseInt(request.getParameter("jumsu"));

ArrayList<Session_DTO> ex1 = (ArrayList<Session_DTO>)session.getAttribute("list"); // DTO

if(ex1 == null) ex1 = new ArrayList<>();

ex1.add(new Session_DTO(name,su)); // 받아오는 값 ex1에 add
session.setAttribute("list", ex1); // 받아온 전체 값 list에 저장해 session에 넘김.
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>학생 점수</h1>
<a href='session_ex1.html'>자료입력</a>
<a href='session_re.jsp'>계산하기</a>
<table border="1" width="70%">
<tr>
	<th>번호</th>
	<th>이름</th>
	<th>점수</th>
<%
	int cou = 1;
for(Session_DTO i:ex1) { // 받아온 값 출력
	Session_DTO lists = i;
	out.println("<tr><td>" + cou + "</td>");
	out.println("<td>" + lists.getName() + "</td>");
	out.println("<td>" + lists.getJumSu() + "</td><tr>");
	cou++;
}
%>
</table>
</body>
</html>