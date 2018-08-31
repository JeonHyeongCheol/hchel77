<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="test2" class="beanpack.ConnBean2"/>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="form" class="beanpack.SangpumForm"/>
<jsp:setProperty property="*" name="form"/>

<%
test2.sangInsert(form);

// 상품 추가 후 목록보기를 호출
//request.getRequestDispatcher("beandb2_page.jsp").forward(request, response); 
// 상품추가 페이지는 forward하게 되면 새로고침 했을 때 계속 추가 됨. 그래서 쓰면 안됨!!!!!!!!!
response.sendRedirect("beandb2_page.jsp"); // 상품추가시는 sendRedirect를 사용하여야 함.

// 여기는 view가 아닌 business 로직이기 때문에 html이 필요 없음.
%>
