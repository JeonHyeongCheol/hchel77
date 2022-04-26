<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function() {
	document.getElementById("btnUpdate").onclick = function() {
		if(confirm("정말 수정할까요?")) {
			f.action = "update.do";
			f.submit();	
		}
	}

	document.getElementById("btnList").onclick = function() {
		f.action = "list.do";
		f.submit();
	}
}
</script>
</head>
<body>
<h2>* 사용자 수정 *</h2><br>
<form name="f" method="post">
userid : ${user.userid}<br>
password : <input type="text" name="password" value="${user.password}"><br>
name : <input type="text" name="name" value="${user.name}"><br>
email : <input type="text" name="email" value="${user.email}"><br>
<button id="btnList">목록</button>
<button id="btnUpdate">완료</button>
<input type="hidden" name="userid" value="${user.userid}">
</form>
</body>
</html>