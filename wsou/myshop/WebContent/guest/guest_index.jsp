<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<table style="width: 100%">
	<tr>
		<td style="font-size: 20px;">전문쇼핑몰</td>
	</tr>
</table>

<%@include file="guest_top.jsp" %>
<table style="width: 100%; height: 480px">
<% if(memid != null) { // 로그인 되었을 때 Page%> 
	<tr>
		<td style="text-align: center;">
		<%= memid %>님! 어서오세요!
		<img src="../images/pic2.gif">
		</td>
	</tr>
<%	} else { // 로그인 하지 않았을 때 Page%>
	<tr>
		<td style="display: inline-block;font-size:20px;width: 100%;height: 600px;background-image:url(../images/pic.jpg);background-size: 60%;background-repeat: no-repeat;text-align: center;background-position: center;">
		고객님 환영합니다<br>
		로그인 후 이용 가능합니다
		</td>
	</tr>
<% } %>  
</table>
<%@include file="guest_bottom.jsp" %>
</body>
</html>