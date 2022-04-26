<!DOCTYPE html>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h2>${message}</h2>
		<hr>
		<h2> 참고사항 </h2>
		View에서 ${sangpumBean.sang} <!-- View 값을 넘기지 않았는데  나오긴 함..뭔이유인지는 알 수 없다. 별명을 안썼을 경우에만 가능. 별명 쓰면 밑에 있는 코드 처럼 사용.-->
		<br>
		Annotation ModelAttribute("값")을 사용 하면 ${my.sang}
		<hr>
		<%
		ArrayList<String> list = (ArrayList)request.getAttribute("ourList");
		for(String s:list) {
			out.print(s + "&nbsp;&nbsp;");
		}
		%>
		<br>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:forEach var="s" items="${ourList}">
			${s}&nbsp; &nbsp;
		</c:forEach>
	</body>
</html>

