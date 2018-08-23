<?xml version="1.0" encoding="UTF-8"?>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
    <!-- contentType="text/html;는 client 에게 보여주기, 알려주기 위함. -->
    <!-- 여기서는 mime(contenttype)타입이 text/xml 사용 -->
    <!-- xml는 태그를 이름을 마음대로 정의 할 수 있음. -->
    <!-- json는 contentType="text/plain; 을 사용 -->
<mysang>
<% //여기는 메소드
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try{
	Class.forName("org.mariadb.jdbc.Driver");
} catch(Exception e) {
	System.out.println("연결 오류 : " + e);
	return;
}

try{
	String url="jdbc:mysql://127.0.0.1:3306/test"; 
	conn = DriverManager.getConnection(url,"root","123");
	pstmt = conn.prepareStatement("select * from sangdata");
	rs = pstmt.executeQuery();
	
	while(rs.next()) {
%>

	<sangpum>
		<code><%=rs.getString("code")%></code>
		<sang><%=rs.getString("sang")%></sang>
		<su><%=rs.getString("su")%></su>
		<danga><%=rs.getString("dan")%></danga>
	</sangpum>

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
</mysang>    
    
