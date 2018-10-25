<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 회원 상세보기 *<br>
<table>
	<tr><td>아이디 : ${member.id}</td></tr>
	<tr><td>비밀번호 : ${member.passwd}</td></tr>
	<tr><td>회원명 : ${member.name}</td></tr>
	<tr><td>등록일 : ${member.reg_date}</td></tr>
	<tr>
		<td colspan="2">
			<a href="list">목록</a>&nbsp;&nbsp;
			<a href="update?id=${member.id}">수정</a>&nbsp;&nbsp;
			<a href="delete?id=${member.id}">삭제</a>
		</td>
	</tr>
</table>
</body>
</html>