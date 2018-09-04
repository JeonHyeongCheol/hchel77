<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="boardMgr" class="my.shop.board.BoardManager"/>
<jsp:useBean id="dto" class="my.shop.board.BoardDto"/>
<%
String num = request.getParameter("num");
String spage = request.getParameter("page");

dto = boardMgr.getReplyData(num);
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>댓글쓰기</title>
<link rel="stylesheet" type="text/css">
<script type="text/javascript">
function check() {
	if(frm.name.value == ""){
		alert("이름을 입력하시오");
		frm.name.focus();
		return;
	}
	
	// 나머진 생략
	frm.submit();
}
</script>
</head>
<body>
<h2>** 댓글쓰기 **</h2>
<form action="replysave.jsp" name="frm" method="post">
<%-- 댓글을 달고 넘겨줄 때 같이 넘겨줘야 할 hidden 값. --%>
<input type="hidden" name="num" value="<%=num %>">
<input type="hidden" name="page" value="<%=spage %>">
<input type="hidden" name="gnum" value="<%=dto.getGnum() %>">
<input type="hidden" name="onum" value="<%=dto.getOnum() %>">
<input type="hidden" name="nested" value="<%=dto.getNested() %>">
<table border="1">
	<tr>
		<td>작성자</td>
		<td><input type="text" name="name" size="15"></td>
	</tr>
	<tr>
		<td>암 호</td>
		<td><input type="password" name="pass" size="15"></td>
	</tr>
	<tr>
		<td>e-Mail</td>
		<td><input type="email" name="mail" size="25"></td>
	</tr>
	<tr>
		<td>제 목</td>
		<td><input type="text" name="title" size="50"
			value="[Re:]:<%=dto.getTitle()%>"></td> <%-- 댓글용 제목 추가 --%>
	</tr>
	<tr>
		<td>글내용</td>
			<td><textarea rows="10" cols="50" name="cont"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="button" value="작 성" onclick="check()">&nbsp;
			<input type="button" value="목 록" onclick="location.href='boardlist.jsp?page=<='"> <%-- 돌아갈 때 가지고온 페이지를 가지고 돌아감. --%>
		</td>
	</tr>
</table>
</form>
</body>
</html>