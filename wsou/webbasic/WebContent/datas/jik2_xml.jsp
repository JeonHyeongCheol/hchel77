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
	if(jik.equals("전체")) {
		pstmt = conn.prepareStatement("select jikwon_no, jikwon_name, jikwon_jik, jikwon_gen, buser_name from jikwon right join buser on buser_num=buser_no");
	} else {
		pstmt = conn.prepareStatement("select jikwon_no, jikwon_name, jikwon_jik, jikwon_gen, buser_name from jikwon right join buser on buser_num=buser_no where jikwon_jik=?");
		pstmt.setString(1, jik);
	}
	rs = pstmt.executeQuery();
	while(rs.next()) {
		
		result += "{";
		result += "\"no\":"+"\"" + rs.getString("jikwon_no") + "\",";
		result += "\"buser\":"+"\"" + rs.getString("buser_name") + "\",";
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
    
    
