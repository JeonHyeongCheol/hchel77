<!-- 페이지 지시어 -->
<%@ page 
language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
안녕 jsp 세상<br>
<% // Scroptlet : JAVA를 사용 할 수 있음.
// 웹에서는 상속이 되지 않음
// 포함관계만 가능함.
// 하지만 상속 관계를 가질 수 있는 방법은 java Bean을 사용하면 가능.
String ir = "홍길동";
out.println(ir + "님의 홈페이지임!!!");
for(int i = 1; i < 7; i++ ) {
	out.print("<h" + i + ">");
	out.print("글자의 모양을 조절");
	out.print("</h" + i + ">");
}
%>
<hr>
여긴 html 영역<br>
<% out.println(ir + "님의 홈페이지임!!!"); %>
<br>
<%// JSP에서 %=는 식임. 출력을 나타냄 %>
<%= ir + "님의 홈페이지임!!!" %> 
<%= new java.util.Date()  %>
<br>
<%
int a = 0, sum = 0;
do {
	a++;
	sum += a;
} while(a < 10);
out.print("sum 은" + sum);
%>
<%= "sum 은" + sum %>
<br>
<%= "tel 은 " + tel %>
<%// %!는 선언임. 클래스의 전역변수로 설정(클래스의 멤버 변수) %>
<%! String tel = "123-1234"; %>
<%// 하지만 메소드를 만들경우에는 그냥 클래스내의 메소드를 얘기함. %>
<%!
public int addData(int m, int n) {
	return m+n;
}
%>
</body>
</html>