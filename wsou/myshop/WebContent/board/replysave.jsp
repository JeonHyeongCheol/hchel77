<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%-- 내부적으로 일어나기 때문에 body Tag 필요 없음 --%>
<jsp:useBean id="bean" class="my.shop.board.BoardBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="boardMgr" class="my.shop.board.BoardManager"/>
<%
String spage = request.getParameter("page");
int num = bean.getNum();
int gnum = bean.getGnum();
int onum = bean.getOnum() + 1; // 그룹내에 num를 변경하기 전 값을 1 Plus 해줌.
int nested = bean.getNested() + 1; // 그룹내에 num를 변경하기 전 들여쓰기 값을 1 Plus 해줌.

// 같은 그룹내에서 새로운 댓글의 onum보다 크거나 같은 값을 댓글 입력전에 먼저 수정하기. 작으면 수정 안함.
boardMgr.updateOnum(gnum, onum);

// 댓글을 저장하기위해 bean(dto)에 값을 넘겨줌.
bean.setOnum(onum);
bean.setNested(nested);
bean.setBip(request.getRemoteAddr());
bean.setBdate();
bean.setNum(boardMgr.currentGetMaxNum() + 1); //댓글도 게시판에 글이기 때문에 게시판 num를 하나 더 해줘야 함.

boardMgr.saveReplyData(bean);
response.sendRedirect("boardlist.jsp?page=" + spage); // 댓글을 단 후 해당페이지로 돌아감.
%>