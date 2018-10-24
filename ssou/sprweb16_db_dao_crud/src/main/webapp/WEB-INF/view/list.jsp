<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>** 회원 자료 **</h2><p/>
<c:if test="${count==0}">
<h3>출력 자료가 없어요.</h3>
<a href="insert"> 추가</a>
</c:if>

<c:if test="${count > 0}">
<table border="1">
	<tr>
		<td colspan="2">
			<a href="insert">추가</a>
		</td>
	</tr>
	<tr>
		<th>id</th><th>name</th>
	</tr>
	<c:forEach var="m" items="${list}">
		<tr>
			<td>${m.id }</td>
			<td><a href="detail?id=${m.id }">${m.name }</a></td>
		</tr>
	</c:forEach>
	
	<!-- 페이징 -->
	<tr>
		<td colspan="2" style="text-align: center;">
			<c:set var="pageCount" value="${(count - 1) / pageSize + 1}" />
			<c:forEach var="p" begin="1" end="${pageCount}">
				<c:if test="${currentPage == p}">${p}</c:if> <!-- 페이지가 같으면 그냥 -->
				<c:if test="${currentPage != p}">
					<a href="list?pageNum=${p}">${p}</a>
				</c:if>
			</c:forEach>
		</td>
	</tr>
</table>
</c:if>
</body>
</html>