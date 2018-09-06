<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="my.shop.member.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="memberMgr" class="my.shop.member.MemberManager"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id = (String)session.getAttribute("idKey"); // input hidden을 안써서 그냥 request로 받아줌. hidden써서 해줘도 됨.
boolean b = memberMgr.memberUpdate(bean, id);

if(b) {
%>
	<script type="text/javascript">
	alert("수정 성공");
	location.href = "../guest/guest_index.jsp";
	</script>
<% } else { %>
	<script type="text/javascript">
	alert("수정 실패\n 관리자에게 문의 바람");
	history.back();
	</script>
<% } %>
</body>
</html>