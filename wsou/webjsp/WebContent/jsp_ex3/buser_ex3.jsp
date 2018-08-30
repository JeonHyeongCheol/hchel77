<%@page import="beanpack.JikwonDb2_ex3"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beanpack.JikwonDto_ex3" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="jikwon" class="beanpack.JikwonDb2_ex3"/>
<%-- <jsp:useBean id="jikwon" class="beanpack.JikwonDb1_ex3"/> --%> <!-- JikwonDb1_ex3 -->
<%-- <jsp:setProperty property="*" name="buser"/> --%> <!-- JikwonDb1_ex3 -->

<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>* 직원자료 *</h1>
<h3><%=request.getParameter("buser")%></h3>
<%-- <h3><%=jikwon.getBuser%></h3> --%>
<table border="1">
	<tr>
		<th>사번</th>
		<th>이름</th>
		<th>직급</th>
		<th>성별</th>
	</tr>
	<%
		ArrayList<JikwonDto_ex3> jiklist = jikwon.getDatas(request.getParameter("buser"));
		//ArrayList<Jikwon_ex3> jiklist = jikwon.getDatas(); //JikwonDb1_ex3
		int cou = 0;
		for(JikwonDto_ex3 s:jiklist) {
			JikwonDto_ex3 dto = s; 
			// 강제 형변환 안해도 됨. 객체 생성 후 넣어주면서 SangpumDto 타입으로 들어갔기 때문에
	%>
	<tr>
		<td><%=s.getJikwon_no() %></td>
		<td><%=s.getJikwon_name() %></td>
		<td><%=s.getJikwon_jik() %></td>
		<% if(s.getJikwon_gen().equals("남")) { %>
			<td style="color: blue;"><%=s.getJikwon_gen() %></td>
		<% } else { %>
			<td style="color: yellow;"><%=s.getJikwon_gen() %></td>
		<%
		   }
		%>
		
	</tr>
	<%
	cou++;
	}
	%>
	<tr>
	<td colspan="4">
	<%=jikwon.getEtcResult() %>
	</td>
</table>
</body>
</html>