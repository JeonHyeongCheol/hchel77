<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>

<jik>
<% //여기는 메소드
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

request.setCharacterEncoding("utf-8");
String jik = request.getParameter("jik");

try{
	Class.forName("org.mariadb.jdbc.Driver");
} catch(Exception e) {
	System.out.println("연결 오류 : " + e);
	return;
}

try{
	String url="jdbc:mysql://127.0.0.1:3306/test"; 
	conn = DriverManager.getConnection(url,"root","123");
	pstmt = conn.prepareStatement("select jikwon_no, jikwon_name, jikwon_jik from jikwon where jikwon_jik=?");
	pstmt.setString(1, jik);
	rs = pstmt.executeQuery();
	

	while(rs.next()) {
%>
	<jikwon>
		<no><%=rs.getString("jikwon_no")%></no>
		<name><%=rs.getString("jikwon_name")%></name>
		<jik><%=rs.getString("jikwon_jik")%></jik>
	</jikwon>
<%
	
	}
	rs.close();
	pstmt.close();
	conn.close();
	} catch(Exception e) {
		System.out.println("처리 실패 : " + e);
		return;
	}
%>
</jik>    
    
