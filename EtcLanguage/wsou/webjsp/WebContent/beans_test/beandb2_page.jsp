<%@page import="beanpack.SangpumForm"%>
<%@page import="beanpack.SangpumDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="test" class="beanpack.ConnBean2"/> 
<%-- 여기서 생성자는 호출되었음 --%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>*상품자료(beans 사용 - paging)*</h2>
<a href="beandb2_ins.html">상품 추가</a>
<hr>
<table border="1">
	<tr>
		<th>코드</th>
		<th>품명</th>
		<th>수량</th>
		<th>단가</th>
	</tr>
	<%
	// 페이지 번호 받아서 해당 페이지 자료만 출력
	
	String pa = request.getParameter("pa");
	
	if(pa == null) pa="1"; // pa가 0이면 1로 만듬
	
	ArrayList<SangpumDto> list = test.getDatas(pa); 
	// getDatas 메소드에 pa를 넘겨줌
	// 받아 올 때 5개로 잘랐기 때문에 5개를 가져옴.
	
	for(int i = 0; i < list.size(); i ++) {
		SangpumDto dto = (SangpumDto)list.get(i); // 가져온 5개를 DTO에 넣어줌. 
	}
	
	
	for(SangpumDto s:list) {
		SangpumDto dto = s; // DTO에 넣은거 빼서 출력.
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
	<tr>
		<td colspan="4" style="text-align: center;">
		<%
		if(test.prepareTotalpage() > 0) {
			for(int pNo = 1; pNo <= test.prepareTotalpage(); pNo++) {
				%>
				<a href="beandb2_page.jsp?pa=<%=pNo%>"><%=pNo%></a>&nbsp; 
				<%
			}			
		}
		 %>
		</td>
	</tr>
</table>
</body>
</html>