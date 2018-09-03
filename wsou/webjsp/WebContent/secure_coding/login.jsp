<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function loginChk() {
	// 입력자료 검사 해야하지만 생략...
	
	document.login.submit();
}
</script>
</head>
<body>
<form action="loginproc.jsp" name="login">
* 로그인 * <br>
사번 : <input type="text" name="no"><br>
이름 : <input type="text" name="name"><br>
<br>
<input type="button" value="로그인" onclick="loginChk()">

</form>
</body>
</html>