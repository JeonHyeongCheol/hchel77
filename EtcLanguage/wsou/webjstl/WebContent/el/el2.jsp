<%@page import="java.util.ArrayList"%>
<%@ 
page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
isELIgnored = "false" 
%>
<% //isELIgnored = "ture" <!-- 이렇게 쓰면 EL 먹지 않음. 기본 false --> %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
EL 연습 : 연산자, 내장객체<br>
\${3 + 4} --> ${3 + 4}<br>
\${5 / 4} --> ${5 / 4}, <!-- ${5 div 4} --><br> <!-- 에러아님 div 사용 가능 -->
\${5 / 0} --> ${5 / 0}<br>
\${5 % 3} --> ${5 % 3}, ${5 mod 3}<br>
\${3 > 4} --> ${3 > 4}, <!-- ${3 gt 4}  --><br>
\${3 <= 4} --> ${3 <= 4}<br>
\${5 > 4 and 3 <= 4} --> ${$5 > 4 and 3 <= 4}<br>
\${5 > 4?10:20} --> ${5 > 4?10:20}<br> <!-- 삼항연산자 -->
<p>--내장객체(EL)--</p>
<%
request.setAttribute("aa", "air");
request.setAttribute("bb", "book");
application.setAttribute("cc", "cat");
%>
<%= request.getAttribute("aa") %> ${requestScope.aa}<br>
<%= request.getAttribute("bb") %> ${requestScope.bb}<br>
<%= application.getAttribute("cc") %> ${applicationScope.cc}<br>
<br>
jsp : <%=request.getHeader("host") %><br> <!-- IP 받기 -->
el : ${header.host} ${header[host]}
<hr>
<%
ArrayList list = new ArrayList();
list.add("kor");
list.add("eng");
request.setAttribute("list", list);
%>
${list[0]} ${list[1]} <!-- 배열 받는 방법. -->

<p>--client의 값 얻기--</p>
<a href="el2.html">el2.html</a><br>
이름 : ${param.irum}, ${param["irum"]}<br> <!-- 불러올수 있는 방법은 2가지. -->
성격 : ${paramValues.sung[0]}, ${paramValues.sung["1"]} <!-- 인덱스에 숫자에 따옴표가 들어가도 상관없음. -->
</body>
</html>