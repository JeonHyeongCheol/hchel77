<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String adminid = (String)session.getAttribute("adminKey");
if(adminid == null){
	response.sendRedirect("adminlogin.jsp"); // 아닐 경우에 관리자 로그인 페이지로 돌아감.
	return;
}
%>
<table style="width: 100%">
	<tr style="background-color: #abc; text-align: center;">
		<td><a href="../index.jsp">홈페이지</a></td>
		<td><a href="adminlogout.jsp">로그아웃</a></td>
		<td><a href="membermanager.jsp">회원관리</a></td>
		<td><a href="productmanager.jsp">상품관리</a></td>
		<td><a href="ordermanager.jsp">주문관리</a></td>								
	</tr>
</table>