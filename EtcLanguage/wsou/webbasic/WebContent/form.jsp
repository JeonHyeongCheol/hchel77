<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 여기에다 자바
String irum = request.getParameter("name"); // JS에서 보낸 것은 request.getParameter로 받음.
String id = request.getParameter("id");
String email = request.getParameter("email");
String nai = request.getParameter("age");

System.out.println(irum + " " + id + " " + email + " " + nai);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
여기는 JSP
JSP!!!!!!!!!!!!!!
<hr>
전달받은 자<br>
이름은 <%=irum %>이고 이메일은 <%=email %>
<%
String abc = "good";
%>
<%=abc %> <!-- 이렇게도 출력 가능 -->
<script type="text/javascript">
var abc="<br>nice";
document.write(abc);
</script>
</body>
</html>