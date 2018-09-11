<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="my.shop.product.OrderManager"/>

<%
String flag = request.getParameter("flag");
String no = request.getParameter("no");
String state = request.getParameter("state");

boolean b = false;
if(flag.equals("update")) {
	b = orderMgr.updateOrder(no, state);
} else if (flag.equals("delete")) {
	
} else {
	response.sendRedirect("ordermanager.jsp");
}

if(b){
%>
	<script>
	alert("정상 처리!");
	location.href= "ordermanager.jsp";
	</script>
	
<%} else {%>
	<script>
	alert("오류 발생\n관리자에게 문의바람!");
	location.href= "ordermanager.jsp";
	</script>
<%
}
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>