<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
   
{"mysang":
[
<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
String result=""; // 여기서는 그냥 씀. 원래 문자열 더하기할 때는 StringBuffer를 사용하여야 함. 

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
	//Thread.sleep(9000); // 스레드 주면 지연시간을 줌. 그 시간이 지난 후 뜸.
	while(rs.next()) {

	result+= "{";
	result+= "\"code\":" + "\"" + rs.getString("code") + "\","; // \" 가 "(따옴표)가 됨.
	result+= "\"sang\":" + "\"" + rs.getString("sang") + "\",";
	result+= "\"su\":" + "\"" + rs.getString("su") + "\",";
	result+= "\"dan\":" + "\"" + rs.getString("dan") + "\""; // 마지막에 ,(콤마)가 없어야 함.
	result+= "},";
	}
	if(result.length() > 0) {
		result = result.substring(0, result.length() -1); // 맨 끝에 ,(콤마) 빼기
	}
	
	out.println(result); // 내장객체에 output. 출력이 되어야지 페이지를 읽어서 출력해줄수 있음.
	rs.close();
	pstmt.close();
	conn.close();
	} catch(Exception e) {
		System.out.println("처리 실패 : " + e);
	}
%>
]
}
    
