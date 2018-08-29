<%@ 
page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
if(!id.equals("abc")) { // 맞지 않으면 다시 돌아가게 함.
	response.sendRedirect("session.html");
	//return; // 서비스 메소드를 빠져나가는 것.
}

session.setAttribute("idkey", id); // session은 키 값이 공유가 가능, request는 공유가 안됨.
session.setMaxInactiveInterval(10); // 20초간 세션 유효.


%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>세션 연습 중...</h2>
<form action="session2.jsp" method="post">
*좋아하는 영화는 ?<br>
<input type="radio" name="movie" value="인크레더블" checked="checked">인크레더블&nbsp;&nbsp;&nbsp;
<input type="radio" name="movie" value="공작">공작&nbsp;&nbsp;&nbsp;
<input type="radio" name="movie" value="어벤저스">어벤저스
<p>
<input type="submit" value="결과보기">
</form>
</body>
</html>