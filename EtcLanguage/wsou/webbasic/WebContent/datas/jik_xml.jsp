<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
{"jik":
[
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
String result="";
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
	pstmt = conn.prepareStatement("select jikwon_no, jikwon_name, jikwon_jik, jikwon_gen from jikwon where jikwon_jik=?");
	pstmt.setString(1, jik);
	rs = pstmt.executeQuery();
	while(rs.next()) {
		
		result += "{";
		result += "\"no\":"+"\"" + rs.getString("jikwon_no") + "\",";
		result += "\"name\":"+"\"" + rs.getString("jikwon_name") + "\",";
		result += "\"jik\":"+"\"" + rs.getString("jikwon_jik") + "\",";
		result += "\"gen\":"+"\"" + rs.getString("jikwon_gen") + "\"";
		result += "},";
		
	}
	
	if(result.length() > 0) {
		result = result.substring(0, result.length() -1);
	}
	out.print(result);
	rs.close();
	pstmt.close();
	conn.close();
	} catch(Exception e) {
		System.out.println("처리 실패 : " + e);
		return;
	}
%>
]
}
    
    

    
