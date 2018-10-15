<%@page import="pack.business.DataDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="processData" class="pack.business.ProcessData"/>
<%
	String code = request.getParameter("code"); // 코드 받음.
DataDto dto = processData.readData(code); // 코드를 넘겨줌.
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 상품 수정 *<br>
<form action="updateok.jsp" method="post">
코드 : <%= dto.getCode() %><br>
<input type="hidden" name="code" value="<%= dto.getCode() %>">
품명 : <input type="text" name="sang" value="<%= dto.getSang()%>"><br>
수량 : <input type="text" name="su" value="<%= dto.getSu()%>"><br>
단가 : <input type="text" name="dan" value="<%= dto.getDan()%>"><br>
<br>
<input type="submit" value="수정완료">
</form>
</body>
</html>	