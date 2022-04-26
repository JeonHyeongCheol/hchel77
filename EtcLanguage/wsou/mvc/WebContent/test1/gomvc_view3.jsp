<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
view3 :
<br>
jsp 방식 : <%= request.getAttribute("result") %>
<hr>
EL 방식 : ${requestScope.result}, ${result} <!-- EL에서 속성값 받을 때 requstScope 생략 가능 -->
</body>
</html>