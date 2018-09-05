<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Admin(관리자용)</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript">
function check() {
	if(frm.id.value === "") {
		frm.id.focus();
		alert("아이디를 입력하시오");
		return;
	}
	if(frm.pwd.value === "") {
		frm.pwd.focus();
		alert("비밀번호를 입력하시오");
		return;
	}
	frm.submit();
}
</script>
</head>
<body>
<form action="adminlogin.jsp" name="frm" method="post">
<table style="width: 100%">
	<tr>
		<td>
		<%
		String sessionValue = (String)session.getAttribute("adminOk"); // 로그인 중과 로그인 하는 화면 두가지 출력을 세션을 이용.
		if(sessionValue != null) {
		%>
		로그인 중입니다.<br>
		[<a href="adminlogout.jsp">로그아웃</a>]
		[<a href="javascript:window.close()">창닫기</a>]
		<%
		} else {
		%>
		<table>
			<tr>
				<td>i d : &nbsp;&nbsp;<input type="text" name="id"></td>
			</tr>
			<tr>
				<td>pwd : <input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td>
				[<a href="#" onclick="check()">로그인</a>]
				[<a href="javascript:window.close()">창닫기</a>]
				</td>
			</tr>
		</table>
		<%
		}
		%>
		</td>
	</tr>
</table>
</form>
</body>
</html>
