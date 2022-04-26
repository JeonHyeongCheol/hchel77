<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 받는 방법 밑에 세가지 : requst, EL

// request
String ss = (String)request.getAttribute("say");
out.println(ss);
%>
<br>
<!-- EL 방법 -->
${requestScope.say}
<br>
${say}

</body>
</html>