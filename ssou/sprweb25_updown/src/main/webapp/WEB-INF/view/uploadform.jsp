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
** 파일 업다운 **
<s:form enctype="multipart/form-data" modelAttribute="uploadFile">
업로드할 파일 선택 : <br>
<input type="file" name="file">
<s:errors path="file" cssStyle="color:red" /><br>
<input type="submit" value="전송">
</s:form>
</body>
</html>