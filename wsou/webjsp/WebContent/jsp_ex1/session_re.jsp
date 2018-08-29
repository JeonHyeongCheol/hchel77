<%@page import="pack.Session_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.getSession(false);

if(session == null) return;

ArrayList<Session_DTO> ex1 = (ArrayList<Session_DTO>)session.getAttribute("list"); // 세션에 저장된 값 가져옴.

if(ex1 == null) return;
%>

<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>학생 성적표</h1>
<table border="1" width="70%">
<tr>
	<th>번호</th>
	<th>이름</th>
	<th>점수</th>
</tr>
<%
	int cou = 1; // 번호
double sum = 0; // 총점
double avg = 0; // 평균
for(Session_DTO i:ex1) { // 받아온 값 출력
	Session_DTO lists = i;
	out.println("<tr><td>" + cou + "</td>");
	out.println("<td>" + lists.getName() + "</td>");
	out.println("<td>" + lists.getJumSu() + "</td><tr>");
	cou++;
	sum += lists.getJumSu();
}
cou = cou -1; // 인원수를 구하기 위해 cou에서 1 뺴줌.
out.println("<tr><td>인원수 : " + cou + "</td>");
out.println("<td>총점 : " + sum + "</td>");
out.println("<td>평균 : " + sum/cou + "</td><tr>");
%>
</table>
<%-- 인원수 : <%=cou %> 총점 : <%=sum %> 평균 : <%=sum/cou %> --%>
<br>
<a href='session_ex1.html'>자료입력</a>
<% session.removeAttribute("list"); // 세션 종료%>
</body>
</html>