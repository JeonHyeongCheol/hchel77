<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="login" class="secure_pack.JikwonLogin_ex5"/>
<%
String jikwon_no = request.getParameter("jikwon_no");
String jikwon_name = request.getParameter("jikwon_name");

boolean check = login.loginCheck(jikwon_no, jikwon_name);

if(check) {
	session.setAttribute("jikwon_no", jikwon_no);
	session.setAttribute("jikwon_name", jikwon_name);
	response.sendRedirect("office_jikwon_main.jsp");
} else {
	%>
	<script type="text/javascript">	
	alert("로그인 실패");
	location.href="office_jikwon_first.jsp";
</script>
	<%
}
%>