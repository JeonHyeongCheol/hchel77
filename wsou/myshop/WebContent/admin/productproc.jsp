<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="productMgr" class="my.shop.product.ProductManager"/>
<%
String flag = request.getParameter("flag");
boolean result = false; // 메소드 갔다 올 때 마다 변수 값을 확인. 기본 false

if(flag.equals("insert")){
	result = productMgr.insertProduct(request); //request에 담긴 내용을 들고 메소드에 감.
} else if(flag.equals("update")) {
	result = productMgr.updateProduct(request);
} else if(flag.equals("delete")) {
	result = productMgr.deleteProduct(request);
} else {
	response.sendRedirect("productmanager.jsp");
}

if(result) {
%>
	<script>
	alert("정상 등록 완료!!");
	location.href = "productmanager.jsp";
	</script>
<% } else { %>
<script>
	alert("등록 실패!!\n관리자에게 문의 바람");
	location.href = "productmanager.jsp";
	</script>
<%
}
%>