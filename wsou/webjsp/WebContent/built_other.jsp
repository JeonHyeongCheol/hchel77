<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
// 내장 객체 1. request jsp에게 값을 넘겨줌
if(id.equals("kor") && pwd.equals("123")) {
	//response.sendRedirect("builtin_obj.jsp"); 
	// 내장 객체 2. 클라이언트를 내어줄 때는 response
	// sendredirect(jsp파일명.jsp); <- 설정된 jsp로 돌아간다는 내장 객체.
}
String[] names = request.getParameterValues("name");
String job = request.getParameter("job");
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 입력된 자료 확인 *<br>
<% // 내장 객체 3. out.println()는 출력 값 내줄 때 %>
아이디 : <% out.println(id); %><br>
이  름 : <% out.println(names[0] + ", 별명은 " + names[1]); %><br>
직업은 <%= job %>
<hr>
기타 정보 : <br>
<% // 내장 객체 4. 밑에는 클라이언트로 부터 받아올 수 있는 내장객체들 %>
client ip : <%= request.getRemoteAddr() %><br>
client domain : <%= request.getRemoteHost() %><br>
method : <%= request.getMethod() %><br>
protocol : <%= request.getProtocol() %><br>
server domain : <%= request.getServerName() %><br>
<br>
Server buffer size : <%= response.getBufferSize() %><br>
Server getCharacterEncoding : <%= response.getCharacterEncoding() %><br>
<br>
<% // 내부 환경 정보 %>
Context path : <%= application.getContextPath() %><br>
Session : <%= pageContext %>
</body>
</html>