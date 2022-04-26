<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<link rel="stylesheet" >
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("btnAdminLogin").onclick = funcAdminLogin;
	document.getElementById("btnHome").onclick = funcHome;
}

function funcAdminLogin() {
	if(adminloginForm.adminid.value === "") {
		alert("ID를 입력하시오");
		adminloginForm.adminid.focus();
		return;
	}
	
	if(adminloginForm.adminpasswd.value === "") {
		alert("ID를 입력하시오");
		adminloginForm.adminpasswd.focus();
		return;
	}
	adminloginForm.submit();
}

function funcHome() {
	location.href = "../index.jsp"
}
</script>
</head>
<body>
<form action="adminloginproc.jsp" name="adminloginForm" method="post">
<table style="position: absolute; left: 50%; top: 40%; width: 300px; height: 150px; margin-left: -150px;">
	<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID : <input type="text" name="adminid"></td>
	</tr>
	<tr>
		<td>PASSWORD : <input type="password" name="adminpasswd"></td>
	</tr>
	<tr align="left">
		<td colspan="2">
		<input type="button" value="관리자 로그인" id="btnAdminLogin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="메인 페이지" id="btnHome">
		</td>
	</tr>
</table>
</form>
</body>
</html>