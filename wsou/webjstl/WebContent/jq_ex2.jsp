<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*"%> 
<%
request.setCharacterEncoding("utf-8");

String flag = request.getParameter("flag"); // 부서인지, 고객인지 확인
String buser = request.getParameter("buser"); // 부서명
String no = request.getParameter("no"); // 직원 번호

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
 
try{
           Class.forName("org.mariadb.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root","123");
}catch(Exception ex){
           System.out.println("연결 오류 : " + ex.getMessage());
           return;
}

try{
		if(flag.equals("buser")) { // flag가 부서명 일 때
			pstmt = conn.prepareStatement("select jikwon_no, jikwon_name, buser_num " + 
					 "from jikwon inner join buser on buser_num = buser_no " +
					 "where buser_name = ?");
			pstmt.setString(1, buser); // 부서명
			rs = pstmt.executeQuery();
			%>
			<jikwons>
			<%
			while(rs.next()){
			%> 
			<jikwon> 
			<bun><%=rs.getString("jikwon_no") %></bun>
			<name><%=rs.getString("jikwon_name") %></name>
			<no><%=rs.getString("buser_num") %></no>  
			</jikwon>
			<%               
			}
			%>
			</jikwons>
			<%
		} else if(flag.equals("gogek")) { // flag가 고객 일 때
			pstmt = conn.prepareStatement("select gogek_name, gogek_tel " + 
										  "from jikwon inner join gogek on jikwon_no = gogek_damsano " +
										  "where jikwon_no = ?");
			pstmt.setInt(1, Integer.parseInt(no)); // 직원번호
			rs = pstmt.executeQuery();
			%>
			<gogeks>
			<%
			while(rs.next()){
			%> 
		
			<gogek> 
				<name><%=rs.getString("gogek_name") %></name>
				<tel><%=rs.getString("gogek_tel") %></tel>
			</gogek>
			<%               
			}
			%>
			</gogeks>
			<%
		}
		
}catch(Exception e){
           System.out.println("처리 오류 : " + e.getMessage());
           return;
}
%> 