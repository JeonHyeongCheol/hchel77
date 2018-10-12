<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 반복문(forEach) * <br>
<c:forEach var="cc" begin="1" end="10" step="2"> <!-- begin : 시작, end : 끝, step : 간격 -->
	<c:out value="${cc}"/> &nbsp;
</c:forEach>
<p>
<c:forEach var="i" begin="1" end="9"> <!-- 구구단 출력 -->
	3 * ${i} = ${1 * 3} &nbsp;
</c:forEach>

<p>
<%
HashMap map = new HashMap();
map.put("name", "신선한");
map.put("today", new Date());
%>
<c:set var="arr" value="<%=new int[]{1,2,3,4,5} %>"/> <!-- 변수안에 배열 선언 -->
<c:set var="m" value="<%=map%>"/>
맵 컬렉션 출력 : <br>
<c:forEach var="k" items="${m}"> <!-- item : 배열 값을 집어 넣을 수 있음. 컬렉션 값 집어 넣음. -->
	${k.key} ^^: ${k.value} <br>
</c:forEach>

<p>
배열출력 : <br>
<c:forEach var="k" items="${arr}" begin="2" end="4">
	${k} &nbsp;
</c:forEach>
<hr>
* 문자열 분학 * <br>
<c:forTokens var="ani" items="horse, cat, dog, pig, tiger" delims="," varStatus="num"> 
<!-- item 값은 직접 줄수도 있음. varStatus를 사용하여 num이 다른 값들을 줄 수 있음.-->
	${num.count}) 동물은 <c:out value="${ani}"/><br>
</c:forTokens> 
<hr>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
숫자 : <fmt:formatNumber value="12345.678" type="number" /> <!-- 포맷 타입을 줄 수도 있으며, taglib에 fmt를 import 해줘야 함. -->
숫자 : <fmt:formatNumber value="12345.678" type="number" pattern="#,##0.0"/> <!-- pattern도 설정 가능. (.)은 소수점 설정 가능. -->
통화 : <fmt:formatNumber value="12345.678" type="currency" currencySymbol="W"/> <!-- currency로 통화 기호 설정 가능. -->

<p>
<c:set var="now" value="<%= new Date() %>"/>
<c:out value="${now}"/>

<p>
<!-- 서식에 대한 얘기, 날짜도 format이 있고, type 설정 가능. -->
날짜 : <fmt:formatDate value="${now}" type="date"/><br>
시간 : <fmt:formatDate value="${now}" type="time"/><br>
모두 : <fmt:formatDate value="${now}" type="both" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/><br> ,<!-- 날짜 패턴 설정 가능. -->

</body>
</html>