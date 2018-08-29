<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String data = request.getParameter("data");
String msg = "message : " + data;

// 1. redirect
//response.sendRedirect("calljsp3.jsp?data=" + msg);

// 2. forward
request.setAttribute("data_key", msg);

ArrayList<String> list = new ArrayList<>();
list.add("tom2");
list.add("james");
list.add("oscar");

request.setAttribute("friends", list); 
// redirect 방식으로 넘길 수 없음. 컬렉션이기 때문에 forward만 가능.

/*
RequestDispatcher dispatcher = request.getRequestDispatcher("calljsp3.jsp");
dispatcher.forward(request, response);
*/
%>
<jsp:forward page="calljsp3.jsp"></jsp:forward> 
<!-- 위에 RequestDispatcher랑 같은 방식임. 이거는 html. 위에는 Java. -->
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>