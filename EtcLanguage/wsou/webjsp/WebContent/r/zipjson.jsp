<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>
{"datas":[
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String result = "";
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		conn = DriverManager.getConnection(url, "scott", "tiger");
	} catch (Exception e) {
		System.out.println("연결 오류:" + e.getMessage());
		return;
	}
	try {
		pstmt = conn.prepareStatement("select * from jikwon");
		rs = pstmt.executeQuery();
		while (rs.next()) {
			result += "{";
			result += "\"jikwon_no\":" + "\"" + rs.getString("jikwon_no") + "\",";
			result += "\"jikwon_name\":" + "\"" + rs.getString("jikwon_name") + "\",";
			result += "\"jikwon_jik\":" + "\"" + rs.getString("jikwon_jik") + "\",";
			result += "\"jikwon_pay\":" + "\"" + rs.getString("jikwon_pay") + "\"";
			result += "},";
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		out.print(result);
	} catch (Exception e) {
		System.out.println("처리 오류:" + e.getMessage());
	}
%>
]}
