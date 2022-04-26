<%@page import="pack.business.JikwonDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="processDao" class="pack.business.ProcessDao" />
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.sel {
width: 90%;
margin: 0 auto;
}

.sel div{
width: 30%;
float: left;
}


div table{
width: 100%;
}
</style>
</head>
<body>
<div class="sel">
<h2>* 직원목록(Mybatis) *</h2>
<div>
	<table border="1">
		<tr>
			<th>사번</th><th>이름</th><th>직급</th><th>성별</th><th>급여</th>
		</tr>
		<%
			ArrayList<JikwonDto> list = (ArrayList<JikwonDto>)processDao.selectDataAll();
		%>
		
		<c:set var="list" value="<%= list %>"/>
		<c:if test="${empty list }"> <!-- list에 자료 없을 때 -->
			<tr>
				<td colspan="5">자료 없음</td>
			</tr>
		</c:if>
		<c:forEach var="m" items="<%= list %>">
		<tr>
			<td>${m.jikwon_no}</td>
			<td>${m.jikwon_name}</td>
			<td>${m.jikwon_jik}</td>
			<td>${m.jikwon_gen}</td>
			<td>${m.jikwon_pay}</td>
		</tr>	
		</c:forEach>
	</table>
</div>

<div style="margin-left: 20px">
	<table border="1">
		<tr>
			<th>직급</th><th>급여합</th><th>급여평균</th>
		</tr>
		<% ArrayList<JikwonDto> list2 = (ArrayList<JikwonDto>)processDao.selectJikPay(); %>
		<c:forEach var="m2" items="<%= list2 %>">
		<tr>
			<td>${m2.jikwon_jik}</td>
			<td><fmt:formatNumber value="${m2.jiksum}" type="number" pattern="#,##0원"/></td>
			<td><fmt:formatNumber value="${m2.jikavg}" type="number" pattern="#,##0원"/></td>
		</tr>
		</c:forEach>
	</table>

</div>

<div style="margin-left: 20px">
	<table border="1">
		<tr>
			<th>성별</th><th>인원수</th><th>급여평균</th>
		</tr>
		<% ArrayList<JikwonDto> list3 = (ArrayList<JikwonDto>)processDao.selectGenPay(); %>
		<c:forEach var="m3" items="<%= list3 %>">
		<tr>
			<td>${m3.jikwon_gen}</td>
			<td><fmt:formatNumber value="${m3.jikcou}" type="number" pattern="#,##0명"/></td>
			<td><fmt:formatNumber value="${m3.jikavg}" type="number" pattern="#,##0.0원"/></td>
		</tr>
		</c:forEach>
	</table>
</div>
</div>
</body>
</html>