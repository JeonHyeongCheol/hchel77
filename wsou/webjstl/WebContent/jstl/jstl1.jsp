<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- tablib : jstl을 불러와야지 사용 할 수 있으며, prefix 값을 줘서 그 값으로 jstl을 사용 할 수 있음. -->
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 변수 연습 *
<c:set var="irum" value="홍길동" scope="page" /> <!-- set : 변수 선언, value : 초기치, scope : 세션-->
<!-- page, request, session application -->
<c:out value="${irum}"/> <!-- 출력 -->
<br>
<c:set var = "ir"> <!-- 이런식으로도 사용 가능. -->
한국인
</c:set>
<c:out value="${ir}"/>
<br>
<c:set var="aa" value="${header['User-Agent']}" scope="page"/>
aa 변수 값은 <c:out value="${aa}"/><br>
<c:remove var="aa" scope="page"/> <!-- 세션을 지우기 위해서는 scope 값이 같아야 함. -->
삭제 후 aa 변수 값은 <c:out value="${aa}"/>
<br>
<c:set var="su1" value="10"/>
<c:set var="su2">
3.5
</c:set>
합은 ${su1 + su2}


<p>
* 조건 판단문 *<br>
<c:set var="bb" value="${'star'}"/>
<c:if test="${bb=='star'}"> <!-- test : 비교 대상( ==, eq 사용), else if가 없으므로 choose문 써야 함. -->
그래 star야
</c:if>
<br>
<c:if test="${bb != null}">
그래 null 아니야
</c:if>

<p>
* 조건 판단문 choose * <br>
<c:choose>
	<c:when test="${bb eq 'moon'}">
	달
	</c:when>
	<c:when test="${bb eq 'star'}">
		<c:out value="${bb}"/>
	</c:when>
	<c:otherwise>어떤 조건도 아닌 경우</c:otherwise> 
</c:choose>

<p>
<c:choose>
	<c:when test="${empty param.msg}"> <!--empty : 빈 값인데, form에 있는 msg 값을 가져옴 -->
	<form>
		입력 : <input type="text" name="msg">
			 <input type="submit" value="확인">
	</form>
	</c:when>
	<c:when test="${param.msg == 'admin'}">
		관리자
	</c:when>
	<c:otherwise>
		환영합니다. 일반회원 <c:out value="${param.msg}"/>
	</c:otherwise>
</c:choose>
</body>
</html>