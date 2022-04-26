<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
** 내장 객체 연습 **<br>
** 회원 가입 **<br>
<form action="built_other.jsp" method="post" name="mem">
아 이 디 : <input type="text" name="id" size="10" value="kor"><br>
비밀번호 : <input type="text" name="pwd" size="10" value="123"><br>
작 성 자 : <input type="text" name="name" size="10" value="홍길동"><br> <!-- name이 똑같으니까 배열 -->
닉 네 임 : <input type="text" name="name" size="10" value="홍두깨"><br>
하 는 일 :
<select name="job">
	<option value="">선택</option>
	<option value="학생">학생</option>
	<option value="회사원">회사원</option>
	<option value="기타">기타</option>
</select>
<p/>
<input type="submit" value="등 록">
</form>
</body>
</html>