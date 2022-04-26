<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
jsp 넘긴 메시지 :
<%
// 1.
//String data = request.getParameter("data");
//out.println("결과 : " + data);

// 2.
String data = (String)request.getAttribute("data_key");
out.println("<br>결과 : " + data);

ArrayList<String> flist = (ArrayList<String>)request.getAttribute("friends");
out.println("<br>나의 친구들 : ");
for(String f:flist) {
	out.println(f);
}
%>
<br>
<a href="WEB-INF/my.jsp">my.jsp</a>
<!-- 클라이언트는 WEB-INF에 들어가지 못하도록 정책상 만들어 놓음. -->
<jsp:forward page="WEB-INF/my.jsp"></jsp:forward> 
<!-- forward는 url에 jsp주소를 보여주지 않기 때문에 사용 가능 -->

</body>
</html>