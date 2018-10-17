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
			f.action = "updateform.do";
			f.submit();	
		}
		
	}
	
	document.getElementById("btnDelete").onclick = function() {
		
		if(confirm("정말 삭제할까요?")) {
			f.action = "delete.do";
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
<h2>* 사용자 상세보기 *</h2>
<table border="1">
	<tr>
		<td>userid : ${user.userid}</td>
		<td>password : ${user.password}</td>
	</tr>
	<tr>
		<td>name : ${user.name}</td>
		<td>email : ${user.email}</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<button id="btnUpdate">수정</button>
			<button id="btnDelete">삭제</button>
			<button id="btnList">목록</button>
		</td>
	</tr>
</table>
<form name="f" method="post">
<input type="hidden" name="userid" value="${user.userid}">
</form>
</body>
</html>