<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Admin(관리자용)</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
</head>
<body>
<table style="width: 100%">
	<tr>
		<td>
		<%session.removeAttribute("adminOk"); %>
		로그아웃 성공!!!!!<br><br>
		[<a href="javascript:window.close()">창닫기</a>]
		</td>
	</tr>
</table>
</body>
</html>
