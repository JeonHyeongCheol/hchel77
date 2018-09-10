<%@page import="my.shop.member.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="my.shop.member.MemberManager"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
<title>회원관리</title>
</head>
<body>
<h2 align="center">** 관리자 : 전체 회원관리 **</h2><br>
<%@ include file="admin_top.jsp" %>
<br>
<hr>
<table style="width: 100%">
	<tr style="background-color: cyan">
		<th>ID</th><th>회원명</th><th>비밀번호</th><th>이메일</th><th>전화</th><th>수정</th>
	</tr>
	<%
	ArrayList<MemberBean> list = memberMgr.getmemberAll();
	for(MemberBean m:list) {
	%>
		<tr>
			<td><%=m.getId() %></td>
			<td><%=m.getName() %></td>
			<td><%=m.getPasswd() %></td>
			<td><%=m.getEmail()%></td>
			<td><%=m.getPhone() %></td>
			<td><a href="javascript:memUpdate('<%=m.getId() %>')">수정하기</a></td>
		</tr>
	<%
	}
	%>
</table>
<%@ include file="admin_bottom.jsp" %>

<form action="memberupdate2.jsp" name="updateFrm" method="post">
<input type="hidden" name="id">
</form>
</body>
</html>