<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
해당 페이지로 이동<p/>
<%
String id = (String)session.getAttribute("id");
// application.setAttribute(arg0, arg1) // 서비스가 죽기전까지 절대 죽지 않음. 거의 쓰지 않음
if(id.equals("admin")) {
	out.println("<a herf='http://www.daum.net'>daum</a>");
} else {
	out.println("<a herf='http://www.naver.com'>naver</a>");
}
%>
</body>
</html>