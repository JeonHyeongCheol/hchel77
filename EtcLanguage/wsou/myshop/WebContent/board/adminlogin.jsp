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
		<%
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		if(id.equals("admin") && pwd.equals("aaa")) {
			session.setAttribute("adminOk", id);
			out.println("로그인 성공<br>");
		} else {
			out.println("로그인 실패<br>");
		}
		%>
		[<a href="javascript:window.close()">창닫기</a>]	
		</td>
	</tr>
</table>

</body>
</html>