<%@page import="beanpack.BangDto_ex4"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bang" class="beanpack.Bang_ex4"/>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function funcUp(code) {
	if(code != "" && code != null) {
		location.href="bangup_ex4.jsp?code=" + code;
	}
}
	
function funcDel(code) {
	if(code != "" && code != null) { 
		if(confirm("정말로 삭제할까요?") == true) {
			location.href="bangdel_ex4.jsp?code=" + code;
		}
	}
}

</script>
</head>
<body>
<h2>* 방명록 글목록*</h2>
<hr>
<br><a href='bangins_ex4.html'>새 글 입력</a>
<table border="1">
	<tr>
		<th>코드</th>
		<th>작성자</th>
		<th>제목</th>
		<th>내용</th>
	</tr>
	<%
	ArrayList<BangDto_ex4> list = bang.getDatas();
	
	for(BangDto_ex4 s:list) {
		BangDto_ex4 dto = s; 
		// 강제 형변환 안해도 됨. 객체 생성 후 넣어주면서 SangpumDto 타입으로 들어갔기 때문에
	%>
	<tr>
		<td><a href="javascript:funcDel(<%=s.getCode() %>)"><%=s.getCode() %></a></td>
		<td><a href="javascript:funcUp(<%=s.getCode() %>)"><%=s.getName() %></a></td>
		<td><%=s.getSubject() %></td>
		<td><%=s.getContent() %></td>
	</tr>
	<%
	}
	%>
</table>
</body>
</html>