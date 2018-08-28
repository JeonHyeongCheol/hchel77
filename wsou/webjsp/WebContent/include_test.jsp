<%@ page 
language="java" 
contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
import="java.util.*"
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="in_top.jsp" %> <!-- 여기 include는 소스 자체를 가져와 그대로 출력. -->
<% 
// include는 JSP안에 JSP를 사용할 때 사용.
// 중복되는 부분을 별도에 파일로 만들어 계속 출력할 때 사용 함.
%>
<h1>파일 포함 연습</h1>
방법 2가지(지시어, action tag)
<br>
<jsp:include page="intag1.jsp"/> 
<!-- 여기 include는 이 자리에 출력해줌. 위에꺼랑 차이를 정확히 알 것. -->
<br>
<jsp:include page="intag2.jsp">
<!-- include 내에서 parameter를 줄 수 있음. --> 
	<jsp:param value="korea" name="msg"/>
</jsp:include>
<pre>



</pre>
<%@ include file="in_bottom.jsp" %>
</body>
</html>