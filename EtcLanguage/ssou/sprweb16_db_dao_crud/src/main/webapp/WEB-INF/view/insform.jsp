<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
** 자료 추가 **
<s:form commandName="command">
아이디 : <s:input path="id" /><br>
비밀번호 : <s:input path="passwd" /><br>
회원명 : <s:input path="name" /><br>
<input type="submit" value="추가">
</s:form>
</body>
</html>