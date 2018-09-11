<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="my.shop.product.CartManager" scope="session"/>
<%--cart는 현재 들어오는 클라이언트에만 살아있기 때문에 scope에 session을 사용하여 사용. --%>
<jsp:useBean id="order" class="my.shop.product.OrderBean"/>
<jsp:setProperty property="*" name="order"/>
<%
String flag = request.getParameter("flag");
String id = (String)session.getAttribute("idKey");

if(id == null) { // 로그인 안했을 때
	response.sendRedirect("../member/login.jsp");
} else { // 로그인 했을 때
	if(flag == null) {
		// Cart에 주문 상품 담기
		order.setId(id);
		cartMgr.addCart(order);
		%>
		<script>
		alert("장바구니에 추가되었쓰~");
		location.href ="cartlist.jsp";
		</script>
		<%
	} else if(flag.equals("update")) {
		order.setId(id);
		cartMgr.updateCart(order);
		%>
		<script>
		alert("장바구니에 내용 수정되었쓰~");
		location.href ="cartlist.jsp";
		</script>
		<%
	} else if(flag.equals("del")) {
		cartMgr.deleteCart(order);
		%>
		<script>
		alert("해당 상품이 삭제되었쓰~");
		location.href ="cartlist.jsp";
		</script>
		<%
	}
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