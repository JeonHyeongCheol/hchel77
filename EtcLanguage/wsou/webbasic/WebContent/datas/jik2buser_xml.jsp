<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h1>부서정보</h1>
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

request.setCharacterEncoding("utf-8");
String buser = request.getParameter("buser");

	

	try{
		Class.forName("org.mariadb.jdbc.Driver");
	} catch(Exception e) {
		System.out.println("연결 오류 : " + e);
		return;
	}
	
	try{
		String url="jdbc:mysql://127.0.0.1:3306/test"; 
		conn = DriverManager.getConnection(url,"root","123");
		pstmt = conn.prepareStatement("select jikwon_no, buser_name, buser_tel from jikwon inner join buser on buser_num=buser_no where buser_name=?");
		pstmt.setString(1, buser);
		rs = pstmt.executeQuery();
		%>
		<div>부서명 : <%=buser%></div><br>
		<%
		int cou = 0;
		String busertel = "";
		while(rs.next()) {
			
			busertel = rs.getString("buser_tel");
			
			cou++;
		}
		%>
		
		<div>전화 : <%=busertel%></div>
		<div>근무인원 : <%=cou%>명</div>
		<%
		rs.close();
		pstmt.close();
		conn.close();
		} catch(Exception e) {
			System.out.println("처리 실패 : " + e);
			return;
	}
%>

</body>
</html>    
    
