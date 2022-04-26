<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//String name = request.getParameter(arg0);
//... 이제 쓰지 않을 것이다... Java Bean이 있으니...
%>

<jsp:useBean id="bean" class="beanpack.FormBean"/> <%-- 객체 생성/클라이언트로 부터 받아오고 밑에 setProperty에서 값을 넘겨줌. --%>
<%-- 
<jsp:setProperty property="name" name="bean"/>
<jsp:setProperty property="kor" name="bean"/>
<jsp:setProperty property="eng" name="bean"/>
--%>
 
<jsp:setProperty property="*" name="bean"/> <%-- 여기서 값을 넘겨줌. FormBean에 --%> 
<!-- property에 *을 넣으면 Bean에 있는 메소드를 알아서 찾아감. -->
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
사용자로 부터 넘어온 자료 출력<br>
이름은 <jsp:getProperty property="name" name="bean"/>
국어은 <jsp:getProperty property="kor" name="bean"/>
영어은 <jsp:getProperty property="eng" name="bean"/>
<br><br>
<jsp:useBean id="examProcess" class="beanpack.ExamProcess"/>
<jsp:setProperty property="formBean" name="examProcess" value="<%=bean %>"/> <%-- value는 examProcess에 bean(formBean)에 값을 줌. --%>
총점은 <jsp:getProperty property="tot" name="examProcess"/>
평균은 <jsp:getProperty property="avg" name="examProcess"/>
</body>
</html>
