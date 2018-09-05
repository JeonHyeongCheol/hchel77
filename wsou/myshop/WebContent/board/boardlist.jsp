<%@page import="my.shop.board.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="boardMgr" class="my.shop.board.BoardManager"/>
<jsp:useBean id="dto" class="my.shop.board.BoardDto"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript">
window.onload = function() {
	document.getElementById("btnSearch").onclick = function() {
		
		if(frm.sword.value === "") {
			frm.sword.focus();
			alert("검색어를 입력하시오");
			return;
		}
		frm.submit();
	}
}
</script>
</head>
<body>
<table style="width: 100%">
	<tr align="center">
		<td>
		[<a href="../index.jsp">메인으로</a>]&nbsp;
		[<a href="boardlist.jsp?page=1">최근목록</a>]&nbsp;
		[<a href="boardwrite.jsp">새글작성</a>]&nbsp;
		[<a href="#" onclick="window.open('admin.jsp','','width=380,height=150,top=200,left=300')">관리자용</a>]&nbsp;
		<br><br>
		<!-- 테이블안에 테이블 만들기 -->
		<table style="width: 100%">
			<tr align="center" style="background-color: silver;">
				<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
			</tr>
		<%
			int pageSu, spage = 1;
			
			request.setCharacterEncoding("utf-8");
			try {
				spage = Integer.parseInt(request.getParameter("page")); // 페이지값 받기.
			} catch(Exception e) {
				spage = 1; // 오류 났을 경우에도 spage 값 1로 변경.
			}
			if(spage <=0) spage = 1; // page값이 음수로 들어와도 1로 줌.
			
			String stype = request.getParameter("stype"); // 이름별 검색, 제목별 검색.
			String sword = request.getParameter("sword"); // 검색 단어.
			
			boardMgr.totalList(); // 전체레코드 수 가져오기.
			pageSu = boardMgr.getPageSu(); // 전체 페이지수 가져오기.
			
			ArrayList<BoardDto> list = boardMgr.getDataAll(spage, stype, sword);
			for(BoardDto i:list) {
				BoardDto dt = i;
				// 댓글 들여쓰기
				int nst = i.getNested();
				String tab = "";
				for(int k=0; k < nst; k++) {
					tab += "&nbsp;&nbsp;";	
				}
				// ----------- 댓글 들여쓰기 끝
		%>
			<tr>
				<td><%=i.getNum() %></td>
				<td>
					<%=tab %> <%-- nested()가 있으면 들여쓰기 됨. --%>
					<a href="boardcontent.jsp?num=<%=i.getNum()%>&page=<%=spage%>"> 
					<%=i.getTitle() %>
					<%-- 전에 page에 돌아기위해서 page 번호를 가져옴 --%></a>
				</td>
				<td><%=i.getName() %></td>
				<td><%=i.getBdate() %></td>
				<td><%=i.getReadcnt() %></td>
			</tr>
		<%
			}
		%>
		</table>
		<br>
		<table >
			<tr>
				<td align="center">
					<%
					for(int i = 1; i <= pageSu; i++) {
						if(i == spage) {
							out.print("<b style='font-size:12pt;color:red;'>[" + i + "]</b>");
						} else {
							out.print("<a href='boardlist.jsp?page=" + i + "'>[" + i + "]</a>");
						}
					}	
					%>
					<br><br>
					<form action="boardlist.jsp" name="frm" method="post">
						<select name="stype">
							<option value="title" selected="selected">글제목</option>
							<option value="name">작성자</option>
						</select>
						<input type="text" name="sword">
						<input type="button" value="검색" id="btnSearch">
					</form>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>