<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//String msg = request.getParameter("msg");
%>
<jsp:useBean id="my" class="beanpack.Para1Class"/> <!-- 클래스를 포함관계로 넣어 줌. -->

<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
* 클래스 멤버 필드에 값 설정하고 참조하기 *<br>
<%
// 우리가 알고 있는 기술 사용
//my.setMsg(msg);
out.println(my.getMsg() + "<br>"); 
%>

<hr>
새 기술 적용<br>
<!-- action tag를 이용한 class 사용 -->
<!-- Property를 쓰게 되면 request.getParameter를 사용하지 않아도 받아옴. -->
<jsp:setProperty property="msg" name="my"/> <!-- value를 줘서 초기값을 줄 수 있음. -->
<jsp:getProperty property="msg" name="my"/>
</body>
</html>