<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>** 회원 수정 **</h2>
<form action="update" method="post">
번호 : <input type="text" name="num" value="${updata.num}" readonly="readonly"><br>
이름 : <input type="text" name="name" value="${updata.name}"><br>
주소 : <input type="text" name="addr" value="${updata.addr}"><br>
<a href="list">목록</a>&nbsp;&nbsp;
<input type="submit" value="수정">
</form>
</body>
</html>