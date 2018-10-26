<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="member" method="post">
name : <input type="text" name="name"><br>
age : <input type="text" name="age"><br>
<input type="submit">
</form>

<br>
<form action="member_xml" method="post">
name : <input type="text" name="name" value="tom"><br>
age : <input type="text" name="age" value="32"><br>
<input type="submit">
</form>
</body>
</html>