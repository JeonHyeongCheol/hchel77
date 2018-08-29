<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
서블릿에 의해 호출된 파일 : <br>
<%

// 1
//request.setCharacterEncoding("utf-8");
//String data = request.getParameter("data");
//out.println("자료는 " + data);

// 2
String data = (String)request.getAttribute("mykey"); // 넘어오는 값이 obj타입이기 때문에 casting해줘야 함.
out.println("자료는(forward) " + data);
%>
</body>
</html>