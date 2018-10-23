<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sfrom" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>자료 입력 : spring tag 사용</h2>
<!-- Spring Tag 사용. -->
<sfrom:form commandName="command"> <!-- 기본적으로  commandName 안쓰면 default가 command임. 그래서 별명인 command 한테 연결 되는 것. -->
	i d : <sfrom:input path="userid"/><br> <!-- path를 사용하면 id, name이 자동으로 만들어 짐. -->
	pwd : <sfrom:input path="passwd"/><br>
	<input type="submit" value="전송">
</sfrom:form>
<hr>
<!-- Spring form을 사용한 것을 페이지소스보기하여 복사 붙여넣기 한 것. -->
<form id="command" action="/sprweb12_call/login" method="post">
	i d : <input id="userid" name="userid" type="text" value=""/><br>
	pwd : <input id="passwd" name="passwd" type="text" value=""/><br>
	<input type="submit" value="전송">
</form>
</body>
</html>
