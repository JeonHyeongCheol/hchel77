<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="product" class="beanpack.Product"/>

<jsp:setProperty property="*" name="product"/> 
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
과일은 <jsp:getProperty property="name" name="product"/>
정가는 <jsp:getProperty property="jungga" name="product"/>
할인액은 <jsp:getProperty property="sale" name="product"/>원
<br><br>
<jsp:useBean id="result" class="beanpack.ProductResult"/>
<jsp:setProperty property="product" name="result" value="<%=product %>"/>
<jsp:getProperty property="result" name="result"/>
</body>
</html>

