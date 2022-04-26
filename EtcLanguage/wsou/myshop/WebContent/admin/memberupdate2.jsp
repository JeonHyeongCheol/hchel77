<%@page import="my.shop.member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="my.shop.member.MemberManager"/>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");

MemberBean bean = memberMgr.getMember(id); // 해당 아이디에 대한 정보를 가져옴.(member에서 만들었음.) 여기는 관리자가 수정
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>회원수정-관리자</title>
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript" src="../js/script.js"></script>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("btnZip").onclick = zipCheck;
	document.getElementById("btnUpdateAdmin").onclick = memUpdateAdminFunc;
	document.getElementById("btnUpdateCancelAdmin").onclick = memUpdateCancelAdminFunc;
}
</script>
</head>
<body>
<br>
<table class="table" style="width: 100%">
<tr>
	<td align="center" valign="middle" >
		<form name="updateFormAdmin" method="post" action="memberupdateproc2.jsp" >
			<table border="1" style="background-color: #FFFFCC">
				<tr align="center" style="background-color: #8899aa">
					<td colspan="2"><b style="color: #FFFFFF">회원 가입</b></td>
				</tr>
				<tr>
					<td width="16%">아이디</td>
					<td width="57%">
					<%=bean.getId() %>님의 정보 수정 중
					<input type="hidden" name="id" value="<%=bean.getId() %>">
					</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="text" name="passwd" size="15" value="<%=bean.getPasswd() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" size="15" value="<%=bean.getName() %>"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" size="27" value="<%=bean.getEmail() %>"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone" size="20" value="<%=bean.getPhone() %>"></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td>
						<input type="text" name="zipcode" size="7" value="<%=bean.getZipcode() %>"> 
						<input type="button" value="우편번호찾기" id="btnZip">
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" size="60" value="<%=bean.getAddress() %>"></td>
				</tr>
				<tr>
					<td>직업</td>
					<td>
						<select name=job>
							<option value="<%=bean.getJob()%>"><%=bean.getJob() %>
							<option value="회사원">회사원
							<option value="학생">학생
							<option value="자영업">자영업
							<option value="주부">주부
							<option value="기타">기타
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정완료" id="btnUpdateAdmin">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="수정취소" id="btnUpdateCancelAdmin">
					</td>
				</tr>
			</table>
		</form>
	</td>
</tr>
</table>
</body>
</html>