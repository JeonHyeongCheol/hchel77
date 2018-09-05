<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="boardMgr" class="my.shop.board.BoardManager"/>
<jsp:useBean id="dto" class="my.shop.board.BoardDto"/>
<%
String num = request.getParameter("num");
String spage = request.getParameter("page");
dto = boardMgr.getData(num);
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript">
function check() {
	if(frm.pass.value === "") {
		frm.pass.focus();
		alert("비밀번호를 입력하시오");
		return;
	}
	if(confirm("정말로 삭제할까요")) {
		frm.submit();
	}
}
</script>
</head>
<body>
<h2>** 글 삭제 **</h2>
<form action="deleteok.jsp" name="frm" method="post">
<input type="hidden" name="num" value="<%=num%>">
<input type="hidden" name="page" value="<%=spage%>">
<table>
	<tr>
		<td>암 호</td>
		<td><input type="password" name="pass" size="15"></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="button" value="삭제" onclick="check()">&nbsp;
			<input type="button" value="목 록" onclick="location.href='boardlist.jsp?page=<%=spage%>'">
		</td>
	</tr>
</table>
</form>
</body>
</html>