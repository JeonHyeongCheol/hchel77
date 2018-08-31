<%@page import="beanpack.SangpumDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String code = request.getParameter("code");%>
<jsp:useBean id="test" class="beanpack.ConnBeanPoling"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
SangpumDto dto = test.updateList(code);

if(dto == null) {
%>	
	<script type="text/javascript">
	alert("등록된 상품코드가 아닙니다\n수정불가")
	location.href="beandb3.jsp";
	</script>
<%
	return;
}
%>
** 상품 수정 ** <br>
<form action="beandb3up_ok.jsp" method="post">
코드 : <%= dto.getCode() %><input type="hidden" name="code" value="<%= dto.getCode() %>"><br>
품명 : <input type="text" name="sang" value="<%=dto.getSang() %>"><br>
수량 : <input type="text" name="su" value="<%=dto.getSu() %>"><br>
단가 : <input type="text" name="dan" value="<%=dto.getDan() %>"><br>
<br>
<input type="submit" value="자료수정">
<input type="button" value="수정취소" onclick="javascript:location.href='beandb3.jsp'">
</form>
</body>
</html>