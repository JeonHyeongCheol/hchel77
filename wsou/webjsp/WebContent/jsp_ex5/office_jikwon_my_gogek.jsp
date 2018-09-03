<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="secure_pack.GogekDto_ex5"%>
<%@page import="java.util.ArrayList"%>
<%
String jikwon_no = (String)session.getAttribute("jikwon_no");

%>
<jsp:useBean id="gogek" class="secure_pack.JikwonLogin_ex5"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" style="width: 80%">
	<tr>
		<th>고객번호</th>
		<th>고객명</th>
		<th>주민번호</th>
		<th>고객전화</th>
	</tr>
	<%
	ArrayList<GogekDto_ex5> gogeklist = gogek.getSelDatas(jikwon_no);
	int cou = 0;
	for(GogekDto_ex5 s:gogeklist) {
		GogekDto_ex5 dto = s; 
		// 강제 형변환 안해도 됨. 객체 생성 후 넣어주면서 SangpumDto 타입으로 들어갔기 때문에
	%>
	
		<tr>
		<td><%=s.getGogek_no() %></td>
		<td><%=s.getGogek_name() %></td>
		<td><%=s.getGogek_jumin() %></td>
		<td><%=s.getGogek_tel() %></td>
	</tr>


	<%
	cou++;
	}
	%>
	<tr>
		<td colspan="4"> 인원수 : <%=cou %> </td>
	</tr>
</table>
</body>
</html>