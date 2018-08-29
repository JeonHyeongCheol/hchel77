<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>* 결과 보기 *</h2>
<%
request.setCharacterEncoding("utf-8");
String movie = request.getParameter("movie");
String id = (String)session.getAttribute("idkey"); // 세션이 가지고 있는 키 값을 id에 넣어줌.
// session은 한 클라이언트에서만 유효, 다른 클라이언트에서 쓸 수 없음(공유X).

if(id != null) { // 세션 값이 있는 것
%>	
	<%= id %>님이 선택한 영화는 <%= movie %>입니다.<br>
	세션 아이디 : <%= session.getId() %><br>
	세션 유지 시간 : <%= session.getMaxInactiveInterval() %>
<%
	//session.invalidate(); // 세션삭제. 여기서는 세션이 20초이기 때문에 주석.
	//session.removeAttribute("idkey"); // idkey값을 가지고 있는 session이 삭제. 
} else {
	out.println("세션키가 사라짐");
}
%>
</body>
</html>