<%@page import="beanpack.SangpumDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="test" class="beanpack.ConnBeanPoling"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function funcUp() {
	var code = prompt("수정할 코드 입력","");
	//console.log(code);
	if(code != "" && code != null) {
		location.href="beandb3up.jsp?code=" + code;
	}
}
	
function funcDel() {
	var code = prompt("수정할 코드 입력","");
	if(code != "" && code != null) { // 삭제 할 때는 꼭 물어봐야한다...................!!!!!!!!
		if(confirm("정말로 삭제할까요?") == true) {
			location.href="beandb3del.jsp?code=" + code;
		}
	}
}

</script>
</head>
<body>
<h2>*상품자료(beans + pooling DB사용)*</h2>
<a href="javascript:funcUp()">수정</a>&nbsp;&nbsp;
<a href="javascript:funcDel()">삭제</a><br><br>
<hr>
<table border="1">
	<tr>
		<th>코드</th>
		<th>품명</th>
		<th>수량</th>
		<th>단가</th>
	</tr>
	<%
	ArrayList<SangpumDto> list = test.getDatas();
	
	for(SangpumDto s:list) {
		SangpumDto dto = s; 
		// 강제 형변환 안해도 됨. 객체 생성 후 넣어주면서 SangpumDto 타입으로 들어갔기 때문에
	%>
	<tr>
		<td><%=s.getCode() %></td>
		<td><%=s.getSang() %></td>
		<td><%=s.getSu() %></td>
		<td><%=s.getDan() %></td>
	</tr>
	<%
	}
	%>
</table>
</body>
</html>