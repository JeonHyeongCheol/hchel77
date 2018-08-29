<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
	Class.forName("org.mariadb.jdbc.Driver");
	String url="jdbc:mysql://127.0.0.1:3306/test"; 
	conn = DriverManager.getConnection(url,"root","123");
	pstmt = conn.prepareStatement("select * from sangdata");
	
	rs = pstmt.executeQuery();
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>* 상품자료 출력 *</h2>
<table border="1">
	<tr>
		<th>code</th><th>sang</th><th>su</th><th>dan</th>
	</tr>
	<%while(rs.next()) { %>
	<tr>
		<td><%= rs.getString(1) %></td>
		<td><%= rs.getString(2) %></td>
		<td><%= rs.getString(3) %></td>
		<td><%= rs.getString(4) %></td>
	</tr>
	<% }
	rs.close();
	pstmt.close();
	conn.close();
%>
</table>
<%
} catch(Exception e) {
	System.out.println("Db Error : " + e);
}
%>
</body>
</html>