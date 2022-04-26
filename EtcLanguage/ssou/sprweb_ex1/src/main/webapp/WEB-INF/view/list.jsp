<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function func() {
	f.submit();
}
</script>
<style type="text/css">
.sel {
	width: 100%;
	margin: 0 auto;
}

.sel div {
	width: 50%;
	float: left;
}

div table iframe{
	width: 100%;
}

</style>
</head>
<body>
	<div class="sel">
		** 직원 자료(@MVC + Mybatis) **	</p>
		<div>
			<table border="1px solid black" style="border-collapse:collapse">
				<tr>
					<th>사번</th>
					<th>이름</th>
					<th>부서명</th>
					<th>직급</th>
					<th>입사년</th>
				</tr>
				<c:forEach var="s" items="${allData}">
					<tr>
						<td>${s.jikwon_no}</td>
						<td>${s.jikwon_name}</td>
						<td><a href="buser?buser=${s.buser_name}" target="frame">${s.buser_name}</a></td>
						<td>${s.jikwon_jik}</td>
						<td>${s.jikwon_ibsail}</td>
					</tr>
					<c:set var="cou" value="${cou+1}" />
				</c:forEach>
				<tr>
					<td colspan="5">인원수 : ${cou}명</td>
				</tr>
				<!-- 검색 -->
				<tr>
					<td colspan="5">
						<form name="f" action="list" method="post">
							<select name="buser_name" onchange="javascript:func()">
								<option value="전체">전체</option>
								<c:forEach var="sel" items="${selbuser}">
									<option value="${sel.buser_name}">${sel.buser_name}</option>
								</c:forEach>
							</select> 
							<input type="submit" value="검색">
						</form>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<iframe name="frame" style="border-style: none;"> </iframe>
		</div>
	</div>
</body>
</html>