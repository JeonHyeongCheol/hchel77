<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="bean" class="my.shop.member.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="memberMgr" class="my.shop.member.MemberManager"/>

<%
boolean b = memberMgr.memberUpdate(bean, bean.getId()); // member에서 만들었던 메소드 사용. 방식은 똑같음.

if(b) {
%>
	<script>
	alert("수정성공");
	location.href = "membermanager.jsp";
	</script>
<%} else {%>
	<script>
	alert("수정실패\n관리자가에게 문의 바람");
	history.back();	
	</script>
<%
}
%>