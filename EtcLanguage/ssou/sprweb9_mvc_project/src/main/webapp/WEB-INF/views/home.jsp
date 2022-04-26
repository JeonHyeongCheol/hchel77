<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<body><h1>Hello world!  </h1>
<P>  The time on the server is ${serverTime}. </P>
<a href="sub1">sub1</a>
<br/>

<a href="sub2?irum=tom">sub2-sub_list2가 보임</a>
<a href="sub2?irum=james">sub2-index가 보임</a>
</body>
</html>