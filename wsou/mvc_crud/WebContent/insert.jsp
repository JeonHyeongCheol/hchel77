<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function() {
	document.getElementById("btnInsert").onclick = function() {
		//alert("a");
		// 입력자료 오류 검사 생략
		f.action = "insert.do";
		f.submit();
	}
	
	document.getElementById("btnList").onclick = function() {
		f.action = "list.do";
		f.submit();
	}
}
</script>
</head>
<body>
* 사용자 추가 *</p>
<form name="f" method="post">
userid : <input type="text" name="userid"><br>
password : <input type="text" name="password"><br>
name : <input type="text" name="name"><br>
email : <input type="text" name="email"><br>
<br>
<input type="button" value="추가" id="btnInsert">
<input type="button" value="목록" id="btnList">
</form>
</body>
</html>