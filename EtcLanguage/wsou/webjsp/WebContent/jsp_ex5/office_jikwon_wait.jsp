<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String jikwon_no = (String)session.getAttribute("jikwon_no"); %>
<% String jikwon_name = (String)session.getAttribute("jikwon_name"); %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="login">
<form>
<h1>HB 물산 직원</h1>
사번 : <%=jikwon_no %>번
이름 : <%=jikwon_name %>
<input type="button" value="로그아웃" onclick="location.href='office_jikwon_logout.jsp'">
</form>
<br>
<a href="office_jikwon_gogek.jsp" target="frame">전체 고객</a> &nbsp;&nbsp;
<a href="office_jikwon_my_gogek.jsp?jikwon_no='<%=jikwon_no %>'" target="frame">내 관리 고객</a>
<br><br>
<iframe name="frame" style="width:80%; height:800px; border-style:none;"></iframe>
</div>
</body>
</html>