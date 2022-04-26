<%@page import="my.shop.member.ZipcodeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="my.shop.member.MemberManager"/>
<%
request.setCharacterEncoding("utf-8");

String check = request.getParameter("check"); // check 확인.
String p_area3 = request.getParameter("area3"); // 동이름 받음.

ArrayList<ZipcodeBean> list = memberMgr.zipRead(p_area3);
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
<script type="text/javascript">
window.onload = function() { // window.onload는 페이지가 열리면 실행 됨.
	document.getElementById("btnZipfind").onclick=dongCheck;
	document.getElementById("btnZipClose").onclick= function() {
		window.close();	
	}
}

function dongCheck() {
	if(zipForm.area3.value === "") {
		alert("검색할 동이름을 입력하시오");
		zipForm.area3.focus();
		return;
	}
	zipForm.submit(); // 자기 자신 페이지를 다시 부름.
}

function send(zip, a1, a2, a3, a4) {
	opener.document.regForm.zipcode.value = zip; // 열려진 홈페이지에 집어넣음. opener : 열려진 페이지를 뜻함.
	opener.document.regForm.address.value = a1 + " " + a2 + " " + a3 + " " + a4;
	window.close(); // 주소 검색창 닫기
}
</script>
</head>
<body>
<form action="zipcheck.jsp" name="zipForm" method="post"> <%-- Form에서도 자기 자신을 부를 수 있음. --%>
<table>
	<tr>
		<td>
		동이름 입력 : <input type="text" name="area3">
		<input type="button" value="검색" id="btnZipfind">
		<input type="button" value="닫기" id="btnZipClose">
		<input type="hidden" name="check" value="n"> <%-- 창이 열릴 때 y, form 전송 시 n으로 변경. 검색 되었는지 안되었는지 확인. --%>
		</td>
	</tr>
</table>
</form>
<hr>
<%
if(check.equalsIgnoreCase("n")) { // 들어오는 값 대소문자 상관없이. 변수 값 들어오는 것 확인. n일 때 주소값 찾기.
	if(list.isEmpty()) {
%>	
	<b> 검색 결과가 없습니다 ! </b>	
<%	} else { %>
	<table>
	<tr>
		<td>검색자료를 클릭하면 자동으로 주소가 입력됩니다</td>
	</tr>
	<tr>
		<td>
		<%
		for(ZipcodeBean i:list) {
			ZipcodeBean bean = i;
			String zipcode = i.getZipcode();
			String a1 = i.getArea1();
			String a2 = i.getArea2();
			String a3 = i.getArea3();
			String a4 = i.getArea4();
			if(a4 == null) a4 = "";
		%>
			<a href="javascript:send('<%=zipcode %>','<%=a1 %>','<%=a2 %>','<%=a3 %>','<%=a4 %>')"><%=zipcode %> <%=a1 %> <%=a2 %> <%=a3 %> <%=a4 %></a><br>
 		<%
		}
		%>
		</td>
	</tr>
</table>
<%		
	}
}
%>

</body>
</html>