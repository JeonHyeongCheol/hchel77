<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
request.setCharacterEncoding("utf-8");
String keyWord = request.getParameter("keyword");
//System.out.println(keyWord);

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
	pstmt = conn.prepareStatement("select jikwon_name from jikwon where jikwon_name like ?");
	pstmt.setString(1, keyWord + "%");
	rs = pstmt.executeQuery();
	ArrayList<String> list = new ArrayList<>(); // 제네릭스
	while(rs.next()) {
		list.add(rs.getString(1));
		System.out.println(rs.getString(1));
	}
	out.print(list.size());
	out.print("|");
	for(int i = 0; i < list.size(); i++) {
		String data = list.get(i);
		out.print(data);
		if(i < list.size() - 1) {
			out.print(",");
		}
	}
	rs.close();
	pstmt.close();
	conn.close();
	} catch(Exception e) {
		System.out.println("처리 실패 : " + e);
		return;
	}
%>
