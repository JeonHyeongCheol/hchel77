<%@ page 
language="java" 
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="java.util.*, java.sql.*" 
import="java.lang.*"
session="true"
buffer="8kb"
autoFlush="true"
isThreadSafe="true"
info="jsp에 대한 문서정보"
errorPage="error.jsp"
%>
<% 
// 위에 정의한거는 Page지시어.
// import는 나열해서 사용 가능.
// session는 사용 한다고 하면 true.
// buffer 기본 값은 8kb 임.
// autoFlush는 메모리를 자동으로 비워 준다는 것.
// isThreadSafe는 쓰레드 사용 여부.
// info는 알려주고 싶은 정보 적음.
// error페이지는 jsp여야 함. err가 나면 error 페이지를 찾아감. 
// 또 다른 방법은 web.xml에 추가하여 사용 가능.
// error 페이지 우선 순위는 1.errorPage 2.web.xml
%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<title>지시어</title>
</head>
<body>
페이지 지시어는 jsp문서 상단에 기술
<p>년도 출력</p>
<%
Calendar cal = Calendar.getInstance();
out.println("올해는 " + cal.get(Calendar.YEAR));
%>
<br>
<%// 내장 객체 사용. 밑에는 info에 대한 정보를 불러오는 메소드 %>
<%=this.getServletInfo() %>
<hr>
<%= 10 / 0 %>
<!-- 500 Err는 컴파일 에러 -->
<br>
<a href="aaa.jsp">aaa 수행</a>
</body>
</html>