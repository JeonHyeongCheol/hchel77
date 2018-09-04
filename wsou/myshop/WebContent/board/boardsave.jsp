<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="my.shop.board.BoardBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="boardMgr" class="my.shop.board.BoardManager"/>

<%
// Max 번호 가져온 후 Bean에 값 넘겨주기 
bean.setBip(request.getRemoteAddr()); // IP 주기
bean.setBdate(); // 날짜
int maxNum = boardMgr.currentGetMaxNum() + 1;
bean.setNum(maxNum); // 게시판 번호
bean.setGnum(maxNum); // 게시판 그룹 번호
boardMgr.saveData(bean); // 저장

response.sendRedirect("boardlist.jsp?page=1");
%>