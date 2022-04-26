<%@page import="beanpack.BangDto_ex4"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String code = request.getParameter("code");%>
<jsp:useBean id="bang" class="beanpack.Bang_ex4"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
BangDto_ex4 dto = bang.updateList(code);

if(dto == null) {
%>	
	<script type="text/javascript">
	alert("등록된 글이 아닙니다\n수정불가")
	location.href="bang.jsp";
	</script>
<%
	return;
}
%>
** 글쓰기 수정 ** <br>
<form action="bangup_ok_ex4.jsp" method="post" name="frm">
<table>
	
	<tr>
		<td>코드 : <%= dto.getCode() %><input type="hidden" name="code" value="<%= dto.getCode() %>"></td>
	</tr>
	<tr>
		<td>작성자 : </td>
		<td><input type="text" name="name" value="<%=dto.getName() %>"></td>
	</tr>
	<tr>
		<td>글제목 : </td>
		<td><input type="text" name="subject" value="<%=dto.getSubject() %>"></td>
	</tr> 
	<tr>
		<td>글내용 : </td>
		<td><textarea name="content" rows="5" cols="30"></textarea></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="submit" value="자료수정">
			<input type="button" value="수정취소" onclick="javascript:location.href='bang.jsp'">
		</td>
	</tr>
</table>   
</form>
</body>
</html>