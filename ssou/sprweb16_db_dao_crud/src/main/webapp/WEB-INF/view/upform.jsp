<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 회원정보 수정 *
<form action="update" method="post">
아이디 : <input type="text" name="id" value="${updata.id}" readonly="readonly">
비밀번호 : <input type="text" name="passwd" value="${updata.passwd}">
회원명 : <input type="text" name="name" value="${updata.name}">
회원가입일 : ${updata.reg_date}
<a href="list">목록</a>&nbsp;&nbsp;
<input type="submit" value="수정">
</form>
</body>
</html>