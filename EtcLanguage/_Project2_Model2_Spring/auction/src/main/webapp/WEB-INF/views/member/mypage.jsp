<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri = "http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./resources/css/commons.css">
    <title>product_upload.jsp</title>
    <script type="text/javascript">
    function ipchalchang(i, j) {
    	
        window.open("", "gg", "width = 600, height = 700 , top = 200, left = 300, scrollbar = no, menubar = no, resizable = no;");

        document.frm1.p_bun.value = i;
        document.frm1.p_sprice.value = j;
        
        document.frm1.action = "ipchal";
        document.frm1.target = "gg";
        document.frm1.method = "post";
        document.frm1.submit();
        
        

    }
    
	function cancle(k) {
    	
    	document.frm2.p_bun.value = k;
        document.frm2.action = "cancling";
        document.frm2.method = "post";
        document.frm2.submit();

    	
	}
	
	function select(l) {
		document.frm2.p_bun.value = l;
        document.frm2.action = "list_detail";
        document.frm2.method = "post";
        document.frm2.submit();
	}
    
    
    </script>
</head>
<body>

<form name="frm1">
    <input type="hidden" name="p_bun" value="">
    <input type="hidden" name="p_sprice" value="">
</form>

<form name="frm2">
    <input type="hidden" name="p_bun" value="">
</form>

<div class="container">

    <div class="col-md-12">
        <!-- Nav -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a href="init.jsp" class="navbar-brand" style="padding: 0;">
                        <img src="./resources/img/logo.png" alt=""
                             style="color: white;" width="70px;" height="44px;" padding="0;">
                    </a>
                </div>
                <!-- // .navbar-header -->

                <ul class="nav navbar-nav navbar-right">
                    <c:set var="sessionlogin" value="${m_id}"/>
                        <c:choose>
                            <c:when test="${sessionlogin == null}">
                                <li><a href="login">로그인</a></li>
                                <li><a href="memberNew">회원가입</a></li>
                            </c:when>
                            <c:otherwise>
	                            <li><a href="list">상품목록</a></li>
	                            <li><a href="mypage">MY PAGE</a></li>
	                            <li><a href="productRegister">판매하기</a></li>
	                            <li><a href="memberUpdate">회원정보수정</a></li>
                                <li><a href="logout">로그아웃</a></li>
                            </c:otherwise>
                        </c:choose>
                </ul>
            </div>
            <!-- // .container(nav) -->
        </nav>
        <!-- // .navbar -->
    </div>
    
    
    <div class="col-md-12" style="margin: 8rem 0 4rem 0;">
        <div class="col-md-12" style="margin-bottom: 4rem;">
            <h1><i class="fa fa-th-list" aria-hidden="true"></i> 내 입찰현황</h1>
        </div>
        <div class="col-md-12 text-center box-shadow table-ta" style="overflow: hidden; height:400px;">
            <table class="table table-hover table-striped text-center">
                <tr>
                    <th class="text-center">상품명</th>
                    <th class="text-center">내 입찰가격</th>
                    <th class="text-center">현재가격</th>
                    <th class="text-center">종료일시</th>
                    <th class="text-center">입찰하기</th>
                </tr>
                <c:forEach var="t" items="${tenderPart}">
                <tr>
                	<td>${t.p_name}</td>
                	<td>${t.t_price}</td>
                	<td>${t.max}</td>
                	<td>${t.p_edate}</td>
                	<td>
                		<input type="button" class="btn btn-primary btn-block" onclick="ipchalchang(${t.t_pbun}, ${t.p_sprice})" value="입찰">

                	</td>
                	
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <!-- my -->
    
    <div class="col-md-12">
        <div class="col-md-12" style="margin-bottom: 4rem;">
            <h1><i class="fa fa-heart" aria-hidden="true"></i> 관심상품</h1>
        </div>
        <div class="col-md-12 text-center box-shadow table-ta" style="overflow: hidden; height:400px;">
            <table class="table table-hover table-striped text-center">
                <tr>
                    <th class="text-center">상품명</th>
                    <th class="text-center">현재가격</th>
                    <th class="text-center">종료일시</th>
                    <th class="text-center">입찰수</th>
                    <th class="text-center">취소</th>
                </tr>
                <c:forEach var="t2" items="${tenderPartLike}">
                <tr>
                	<td><a href = "#" onclick = "javascript:select(${t2.p_bun})">${t2.p_name}</a></td>
                	<td>${t2.max}</td>
                	<td>${t2.p_edate}</td>
                	<td>${t2.count}</td>
                	<td>
                		<input type="button" class="btn btn-blcok btn-danger" onclick="cancle(${t2.p_bun})" value="좋아요 취소">
                	</td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>


<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>
<script src="./resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./resources/js/product.js"></script>
</div>
</body>
</html>
