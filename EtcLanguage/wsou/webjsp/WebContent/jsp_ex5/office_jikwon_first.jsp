<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="login">
<form action="office_jikwon_ok.jsp" method="post">
<h1>HB 물산 직원</h1>
사번 : <input type="text" name="jikwon_no" size="10">
이름 : <input type="text" name="jikwon_name" size="10">
<input type="submit" value="로그인">
</form>
<br>
전체 고객 &nbsp;&nbsp; 내 관리 고객
<br><br>
<table border="1" style="width: 80%">
	<tr>
		<th>고객번호</th>
		<th>고객명</th>
		<th>주민번호</th>
		<th>고객전화</th>
	</tr>
	<tr>
		<td colspan="4"> 인원수 : </td>
	</tr>
</table>
</div>
</body>
</html>