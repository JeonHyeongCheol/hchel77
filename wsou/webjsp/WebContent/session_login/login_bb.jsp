<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="aa">
	<form action="login_ok.jsp" method="post">
		<h2>강남센터</h2>
		<%=session.getAttribute("id") + "님이 로그인 중" %>
		i d : <input type="hidden" name="id">
		pwd : <input type="hidden" name="pwd">
		<input type="submit" value="로그아웃">
	</form>
<p/><a href="jikwon.jsp" target="frame">자료보기</a>
</div>