<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./resources/css/commons.css">
    <link rel="stylesheet" href="./resources/css/imagelistexpander.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <title>list.jsp</title>
    <style>
        html {
            overflow: scroll;
            overflow-x: hidden;
        }
        ::-webkit-scrollbar {
            width: 0px;
            background: transparent;
        }
        ::-webkit-scrollbar-thumb {
            background: #FF0000;
        }
    </style>
    <script type="text/javascript">
        var timer = "";
        var nowtime = "";
        var endtime = "";

        var gap = "";

        var day = "";
        var hour = "";
        var minute = "";
        var second = "";
        var settime = 1000;

        function picture(i) {
            clearInterval(timer);
            enddate = i;

            nowtime = new Date();
            endtime = new Date(enddate);

            gap = endtime.getTime() - nowtime.getTime();

            day = Math.floor(gap/1000/60/60/24);
            hour = Math.floor(gap/1000/60/60) - (24 * day);
            minute = Math.floor(gap/1000/60) - (60 * hour) - (60 * 24 * day);
            second = Math.floor(gap/1000) - (60 * minute) - (60 * 60 * hour) - (60 * 60 * 24 * day);

            // 초기화
            $(".countTimeDay").html(day);
            $(".countTimeHour").html(hour);
            $(".countTimeMinute").html(minute);
            $(".countTimeSecond").html(second);


            timer = setInterval(function () {
                //초기값
                // 설정
                $(".countTimeDay").html(day);
                $(".countTimeHour").html(hour);
                $(".countTimeMinute").html(minute);
                $(".countTimeSecond").html(second);

                if(second == 0 && minute == 0 && hour == 0){
                    alert('타이머 종료.');
                    clearInterval(timer); /* 타이머 종료 */
                }else{
                    second--;


                    // 분처리
                    if(second == 0){
                        minute--;
                        second = 59;
                    }


                    //시간처리
                    if(minute == 0){
                        if(hour > 0){
                            hour--;
                            minute = 59;
                        }
                    }


                    // 일 처리
                    if(hour == 0){
                        if(day > 0){
                            day--;
                            hour = 23;
                        }
                    }
                }
            }, settime); /* millisecond 단위의 인터벌 */
        }

        function gogo(i) {
            document.frm1.p_bun.value = i;
            document.frm1.method = "post";
            document.frm1.action = "list_detail";
            document.frm1.submit();
        }

        function gogo2(i) {
            document.getElementById("searchtext").value = i;
            document.getElementById("p_name").value = i;
            init();
        }

        function gogo3() {
            document.getElementById("p_name").value = document.getElementById("searchtext").value;
            document.frm4.method = "post";
            document.frm4.action = "ajaxlist";
            document.frm4.submit();
        }

        function gogo4() {
            document.getElementById("p_name").value = "anything";
            document.frm4.method = "post";
            document.frm4.action = "ajaxlist";
            document.frm4.submit();
        }

        var ajax;
        var ready = false;
        var sendvoca = "";


        function start() {
            ready = true;
            setTimeout("send()", 1000);
        }


        function send() {
            if(ready === false) {
                return;
            }

            var nowvoca = document.getElementById("searchtext").value;

            if(nowvoca === "") {
                sendvoca = "";
                hide("parent");
            }else if(nowvoca !== sendvoca) {
                sendvoca = nowvoca;

                if(sendvoca != "") {

                    var baby = "baby="+sendvoca;
                    ajax = new XMLHttpRequest();
                    ajax.open("post", "ajaxgo", true);
                    ajax.onreadystatechange = function() {
                        if(ajax.readyState === 4) {
                            if(ajax.status ===200) {
                                receive();
                            }
                        }
                    }
                    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
                    ajax.send(baby);

                }
            }
        }

        function hide(parent) {
            var imsi = document.getElementById(parent);
            if(imsi) {
                imsi.style.display = "none";
            }
        }

        function show(parent) {
            var imsi = document.getElementById(parent);
            if(imsi) {
                imsi.style.display = "";
            }
        }

        function receive() {

            var text = ajax.responseXML;
            var item = text.getElementsByTagName("product");
            var name = text.getElementsByTagName("name");
            var output = "";


            for(var i = 0; i<item.length; i++) {
                output += "<a href='javascript:void(0)' style='cursor:pointer;' onclick = 'gogo2(\""+name[i].childNodes[0].nodeValue+"\");'>" + name[i].childNodes[0].nodeValue +"</a><br>";
            }

            document.getElementById("child").innerHTML = output;
            show("parent");
        }

        function init() {
            ready = false;
            sendvoca = "";
            hide("parent");
        }
    </script>
</head>
<body>
<div class="container-fluid">

    <!-- Nav -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a href="init.jsp" class="navbar-brand" style="padding: 0;">
                    <img src="./resources/img/logo.png" alt="logo"
                         style="color: white; width: 70px; height: 44px;">
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

    <div class="container" style="margin-top: 8rem;">

        <div class="col-md-12">
            <h1><i class="fa fa-list-alt" aria-hidden="true"></i> Product</h1>
        </div>

        <!-- Search bar -->
        <div class="search-bar col-md-12">
            <div class="col-md-9 col-md-offset-3 form-group">

                <!-- ajax search -->
                <form name="index_search" class="form-inline">
                    <input onkeydown = "start();" id = "searchtext" name = "search" placeholder="Enter Keyword" class="form-control search-mg" type="search">
                    <a href="#" onclick="gogo3();" class="btn search-btn" style="background-color: #febd69; color: black;">
                        <i class="fa fa-search" aria-hidden="true"></i>
                    </a>
                </form>
                <div id = "parent" style="display: ; padding-left: 1.2rem;" class="col-md-12">
                    <div id = "child" class="col-md-12" style="padding: 0;">
                    </div>
                </div>
                <!-- // ajax search -->
            </div>
            <!-- //.form-group -->
        </div>
        <!-- //.search-bar -->

        <!-- hash tag -->
        <div class="tags-block col-md-12">
            <div class="btn-tags">
                <ul>
                    <li><a href="#">tags</a></li>
                    <li><a href="#">border</a></li>
                    <li><a href="#">input:focus</a></li>
                    <li><a href="#">header</a></li>
                    <li><a href="#">flex</a></li>
                    <li><a href="#">background</a></li>
                    <li><a href="#">decoration</a></li>
                    <li><a href="#">outline</a></li>
                </ul>
            </div>
        </div>


        <ul class="gallery-items">

            <c:forEach var="i" items="${plist}">
                <li class="gallery-item">
                    <div class="gallery-contents">
                        <div class="thumbnail gallery-trigger shrink">
                            <img onclick="picture('${i.p_edate}')" src="./resources/img/${i.picture}" alt="main-image"/>
                        </div>
                        <div class="title">${i.p_title}</div>
                    </div>

                    <div class="gallery-expander">
                        <div class="gallery-expander-contents">
                            <div class="gallery-trigger-close close">
                                <i class="fa fa-times fa-3x" aria-hidden="true"></i>
                            </div>
                            <div class="col">
                                <div class="image">
                                    <img src="./resources/img/${i.picture}" alt="sub-image" />
                                </div>
                            </div>
                            <div class="col">
                                <div class="title">
                                    <h3>${i.p_name}</h3>
                                </div>
                                <div class="contents text-center">
                                    <div class="col-md-4">현재가</div>
                                    <div class="col-md-8"><span style="font-size: 36px;">${i.nowprice}원</span></div>
                                    <div class="col-md-4">입찰수</div>
                                    <div class="col-md-8 color-red">${i.count -1}명 입찰중</div>
                                    <div class="col-md-4">경매마감</div>
                                    <div class="col-md-8">
                                        <strong>남은 시간</strong>
                                        <span class="countTimeDay">60</span>일
                                        <span class="countTimeHour">60</span>시
                                        <span class="countTimeMinute">60</span>분
                                        <span class="countTimeSecond">60</span>초<br>
                                        <span id="edate">(종료 : ${i.p_edate })</span>
                                    </div>
                                </div>
                                <div class="col-md-12" style="margin-top: 4rem;">
                                    <input onclick = "gogo(${i.p_bun})" type="button" value="상세보기" class="btn btn-info col-md-offset-5">
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script src="./resources/js/jquery-3.3.1.min.js"></script>
<script src="./resources/js/bootstrap.min.js"></script>
<script src="./resources/js/component.js"></script>
<script src="./resources/js/jquery.imagelistexpander.min.js"></script>
<script>
    (function(global, $){
        $('.gallery-items').imagelistexpander({
            prefix: "gallery-"
        });
    })(this, jQuery)
</script>
</body>
<form name = "frm1">
    <input type="hidden" name = "p_bun">
</form>
<form name="frm4">
    <input id = "p_name" name = "p_name" type="hidden" value = "anything">
</form>
</html>