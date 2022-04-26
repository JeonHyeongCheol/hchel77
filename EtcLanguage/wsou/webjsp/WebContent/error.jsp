<%@ page 
language="java" 
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
isErrorPage="true"
%>
<% // isErrorpage에 true를 할 경우 이페이지는 error 페이지로 사용한다는 뜻. %>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<title>에러용</title>
</head>
<body>
에러 발생<br>
<% // 내장 객체 사용. 메세지 출력용 %>
<%= exception.getMessage() %>
</body>
</html>