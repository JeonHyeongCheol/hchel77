<%@page import="co.kr.auction.model.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <link rel="stylesheet" href="./resources/css/commons.css">
    <title>updateMember.jsp</title>
    <style>
        #memWrap {
            overflow: hidden;
            margin: 16rem 0 6rem 0;
            background-color: white;
        }
        .upd-img {
            width: 42%;
        }
    </style>
</head>
<body>

<div class="container">

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


    <!-- row! -->
    <div class="col-md-12">

        <div id="memWrap" class="col-md-12 box-shadow box-round">

            <div class="col-md-6" style="margin-top: 5rem;">
                <!-- VICO images -->
                <div class="col-md-12 text-center">
                    <img src="./resources/img/update.png" alt="update" class="upd-img">
                    <h1 style="margin-top: 2.6rem;">Modify Member</h1>
                </div>
                <div class="col-md-12" style="margin-top: 4rem;">
                    <h4 style="line-height: 3rem;"><i class="fa fa-info-circle" aria-hidden="true"></i>
                        정보를 수정할 경우에는 <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;비밀번호를 꼭 다시 한번 확인해 주세요.</h4>
                    <br>
                    <h4 style="line-height: 3rem;"><i class="fa fa-info-circle" aria-hidden="true"></i>
                        SMS와 메일 수신에 동의하시면, 매일 매일 선택하신 <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;관심지역의 딜 정보와 다양한 이벤트, <br>
                        &nbsp;&nbsp;&nbsp;&nbsp;각종 할인/상품 정보에 대한 안내를 받아보실 수 있습니다.</h4>
                </div>
            </div>

            <div class="col-md-6" style="margin-top: 3rem;">
            <s:form name="fr" action="memberUpdate" modelAttribute="upmemberBean">
            
                <!-- Name : readonly -->
                <div class="input-group">
                <span class="input-group-addon">
                <i class="fa fa-2x fa-user-o fa-fw" aria-hidden="true"></i></span>
                    <input type="text" id="m_name" name="m_name" class="form-control input-height log-width" value="${mUpdateData.m_name}" readonly="readonly">
                </div><br>

                <!-- ID : readonly -->
                <div class="input-group">
                <span class="input-group-addon">
                <i class="fa fa-2x fa-id-badge fa-fw" aria-hidden="true"></i></span>
                    <input type="text" id="m_id" name="m_id" class="form-control input-height log-width" value="${mUpdateData.m_id}" readonly="readonly">
                </div><br>

                <!-- PASSWORD -->
                <div class="input-group">
                <span class="input-group-addon">
                <i class="fa fa-2x fa-unlock-alt fa-fw" aria-hidden="true"></i></span>
                    <input type="password" id="m_passwd" name="m_passwd" class="form-control input-height log-width" placeholder="비밀번호를 입력하세요." aria-describedby="PwdHelp">
                </div>
                <span id="PwdHelp" class="help-block">8~12자 이내의 비밀번호를 입력해 주세요.</span><br>

                <!-- PASSWORD -->
                <div class="input-group">
                <span class="input-group-addon">
                <i class="fa fa-2x fa-unlock-alt fa-fw" aria-hidden="true"></i></span>
                    <input type="password" id="m_passwd2" class="form-control input-height log-width" placeholder="한번 더 입력해주세요.">
                </div><br>

                <!-- CALLPHONE -->
                <div class="input-group">
                <span class="input-group-addon">
                <i class="fa fa-2x fa-mobile fa-fw" aria-hidden="true"></i></span>
                    <input type="text" id="m_phone" name="m_phone" class="form-control input-height log-width" value="${mUpdateData.m_phone}" placeholder="000-0000-0000">
                </div><br>

                <!-- EMAIL -->
                <div class="input-group">
                <span class="input-group-addon">
                <i class="fa fa-2x fa-envelope-o fa-fw" aria-hidden="true"></i></span>
                    <input type="text" id="m_email" name="m_email" class="form-control input-height log-width" value="${mUpdateData.m_email}" placeholder="형식에 맞춰 입력해주세요.">
                </div><br>

                <!-- ADDR-1 -->
                <div class="input-group">
                <span class="input-group-addon">
                <i class="fa fa-2x fa-address-card-o fa-fw" aria-hidden="true"></i></span>
                    <input type="text" id="m_addr1" name="m_addr1" class="form-control input-height log-width" value="${mUpdateData.m_addr1}" placeholder="주소찾기를 이용해 주세요.">
                </div><br>
                <input type="button" value="주소찾기" class="form-control" onclick="jusosearch()">
                <br>

                <!-- ADDR-2 -->
                <div class="input-group">
                <span class="input-group-addon">
                <i class="fa fa-2x fa-address-card-o fa-fw" aria-hidden="true"></i></span>
                    <input type="text" id="m_addr2" name="m_addr2" class="form-control input-height log-width" value="${mUpdateData.m_addr2}" placeholder="상세주소를 입력해주세요.">
                </div><br>


                <div class="col-md-12" style="margin-bottom: 4rem;">
                    <div class="col-md-6">
                        <button class="btn btn-info btn-log btn-block" type="submit">Submit</button>
                    </div>

                    <div class="col-md-6">
                        <button class="btn btn-danger btn-block" type="button" onclick="Main()">Cancle</button>
                    </div>
                </div>
			</s:form>
            </div>
            <!--  -->
        </div>
        <!-- //#memWrap -->

    </div>
    <!-- //.row -->
</div>
<!-- //.container -->

<script src="./resources/js/jquery.com_jquery-3.3.1.slim.js"></script>
<script src="./resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./resources/js/member.js"></script>
</body>
</html>
