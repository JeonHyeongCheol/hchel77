<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
[
<%
request.setCharacterEncoding("utf-8");

String jik = request.getParameter("jik"); // 직급 받기

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
String result = "";
try{
	Class.forName("org.mariadb.jdbc.Driver");
	String url="jdbc:mysql://127.0.0.1:3306/test"; 
	conn = DriverManager.getConnection(url,"root","123");
}catch(Exception e){
           System.out.println("연결 오류:" + e.getMessage());
           return;
}
try{
		if(jik == null) { // 전체 목록
			pstmt = conn.prepareStatement("select jikwon_no, jikwon_name, jikwon_jik, buser_name " + 
					"from jikwon inner join buser on buser_num = buser_no order by jikwon_no asc");
		} else { // 직급 목록
			pstmt = conn.prepareStatement("select jikwon_no, jikwon_name, jikwon_jik, buser_name " + 
					"from jikwon inner join buser on buser_num = buser_no where jikwon_jik = ? order by jikwon_no asc");
			pstmt.setString(1, jik);
		}
           rs = pstmt.executeQuery();
           while(rs.next()){
                     result += "{";
                     result += "\"jikwon_no\":" + "\"" + rs.getString("jikwon_no") + "\",";
                     result += "\"jikwon_name\":" + "\"" + rs.getString("jikwon_name") + "\",";
                     result += "\"jikwon_jik\":" + "\"" + rs.getString("jikwon_jik") + "\",";
                     result += "\"buser_name\":" + "\"" + rs.getString("buser_name") + "\"";
                     result += "},";
           }
           if(result.length() > 0){
                     result = result.substring(0, result.length() - 1);
           }
           out.print(result);
}catch(Exception e){
           System.out.println("처리 오류:" + e.getMessage());
}
%>
]