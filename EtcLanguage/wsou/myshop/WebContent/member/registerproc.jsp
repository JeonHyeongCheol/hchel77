<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="memBean" class="my.shop.member.MemberBean"/>
<jsp:setProperty property="*" name="memBean"/>
<jsp:useBean id="memberMgr" class="my.shop.member.MemberManager"/>

<% boolean b = memberMgr.memInsert(memBean); // 회원가입을 위해 정보를 넘김 Bean에 넘김 후 메소드 수행 후 결과 값 받아옴.%>

<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(b) {
	out.println("<b>회원가입을 축하합니다</b><br>");
	out.println("<a href='login.jsp'>로그인</a>");
} else {
	out.println("회원가입 실패!!! 관리자에게 문의 바람<br>");
	out.println("<a href='register.jsp'>가입 재시도</a>");
}
%>
</body>
</html>