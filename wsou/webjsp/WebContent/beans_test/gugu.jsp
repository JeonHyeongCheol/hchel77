<%@page import="beanpack.Gugudan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>** 현재 나의 기술로 할 수 있는 방법 ** </h1><br>
<%
int dan = Integer.parseInt(request.getParameter("dan"));
out.print(dan + "단 출력<p>");

// 클래스의 포함 관계 // 이렇게 만들게 되면 새롭게 객체가 만들어짐. 서블릿처럼 init()으로 빼야함.
//Gugudan gugudan = new Gugudan();  

// 밑에 방법이 싱글톤 처리
Gugudan gugudan = Gugudan.getInstance(); 
System.out.println(gugudan.toString());

int re [] = gugudan.compute(dan);

for(int a = 0; a < 9; a++) {
	out.println(dan + "*" + (a + 1) + "=" + re[a] + "&nbsp;&nbsp;");
}
%>
<hr>
<h1>** Beans 기술로 할 수 있는 방법</h1>
<!-- model 1 방식 -->
<jsp:useBean id="gugu" class="beanpack.Gugudan" scope="session"/>
<!-- id가 객체가 됨. class명 정해줌-->
<%
System.out.println(gugu.toString());
int re2 [] = gugu.compute(dan);
for(int a = 0; a < 9; a++) {
	out.println(dan + "*" + (a + 1) + "=" + re2[a] + "&nbsp;&nbsp;");
}
%>
</body>
</html>