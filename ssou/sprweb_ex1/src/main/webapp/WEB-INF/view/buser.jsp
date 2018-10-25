<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
** 부서 자료 **
<table border="1px solid black" style="border-collapse:collapse">
	<tr>
		<th>부서번호</th><th>부서명</th><th>전화</th><th>위치</th>
	</tr>
	<tr>
		<th>${buser.buser_no}</th>
		<th>${buser.buser_name}</th>
		<th>${buser.buser_tel}</th>
		<th>${buser.buser_loc}</th>
	</tr>
</table>
</body>
</html>