<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String id = (String)session.getAttribute("idKey");
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" >
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("btnLogin").addEventListener("click", funcLogin, false);
	document.getElementById("btnNewMember").addEventListener("click", funcNew, false);
}

function funcLogin() { // button올 되있어서 eventlistener와 메소드 사용하여 값을 넘김.
	//alert("a");
	if(loginForm.id.value === "" || loginForm.passwd.value === "" ){
		alert("ID 및 PASSWORD를 입력하세요");
	} else {
		loginForm.action = "loginproc.jsp";
		loginForm.method = "post";
		loginForm.submit();
	}
}

function funcNew() {

}

</script>
</head>
<body>
<div style="position:relative; width: 500px; height: 600px ; margin: 150px auto;">
<table style="position: absolute; width: 300px; height: 150px; margin-left: -40px; margin-top: 0px;">
		<tr>
			<td>
				<img src="../images/1.png">
			</td>
		</tr>
</table>
<%
if(id != null) {
	response.sendRedirect("../guest/guest_index.jsp");
%>
	<%-- 
	<b><%=id %>님 환영합니다</b><p/>
	준비된기능을 사용할 수 있습니다<br>
	<a href="logout.jsp">로그아웃</a>.
	--%>
<% } else { %>
	<form name="loginForm">
	<table style="position: absolute; left: 50%; top: 45%; width: 300px; height: 150px; margin-left: -150px;">
		<tr><td> ** 로그인 ** </td></tr>
		<tr>
			<td> 아이디 : </td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td> 비밀번호 : </td>
			<td><input type="text" name="passwd"></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="button" value="로 그 인" id="btnLogin">
			<input type="button" value="회원가입" id="btnNewMember">
			</td>
		</tr>
	</table>
	</form>
<%	
}
%>
</div>
</body>
</html>