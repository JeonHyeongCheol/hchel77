<%@page import="co.kr.auction.model.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./resources/css/commons.css">
    <script src="./resources/js/jquery.com_jquery-3.3.1.slim.js"></script>
    <script src="./resources/js/bootstrap.min.js"></script>
    <title>index.jsp</title>
    <script>

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
                };
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
        
        function catego(i) {
            document.frm2.p_category.value = i;
            document.frm2.action = "list2";
            document.frm2.method = "post";
            document.frm2.submit();
        }
    </script>
</head>
<body>
<form name="frm2">
    <input type="hidden" name="p_category" value="">
</form>
<div class="container-fluid" style="overflow: hidden;">

    <div id="Header" class="container-fluid">

        <div class="header-img">

            <!-- header text-->
            <div class="head-text">
                <h1 class="text-center">Welcome!</h1>
            </div>


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
                                <li><a href="list" onclick = "gogo4();">상품목록</a></li>
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
        <!-- header-img -->

    </div>
    <!-- // #Header -->


    <div id="Contents" class="container-fluid">

        <div id="ConWrap" class="col-md-12">

            <!-- Search bar -->
            <div class="search-bar col-md-12">
                <div class="col-md-9 col-md-offset-4 form-group">

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



      <div id="Left" class="col-md-2">

                <!-- Categories -->
                <div class="cates-block">
                    <h3>Categories</h3>
                    <div class="cates-ul" style="margin: 1rem 0 2rem 0;">
                        <c:forEach var="c" items="${clist}">
                            <a onclick="javascript:catego('${c.category_name}')">${c.category_name}</a>
                        </c:forEach>
                    </div>
                </div>


        <!-- Tags -->
        <div class="tags-block">
          <h3>Popular</h3>
            <div class="btn btn-tags" style="margin-top: 1rem;">
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


      </div>
      <!-- // #Left -->


      <!-- Image -->
      <div id="Right" class="col-md-10">
        <div class="col-md-12">

                    <c:forEach var = "i" items="${plist}">

                          <!-- col-md-4, image section start -->
                          <div class="card-mg col-md-4">
                            <div class="card card-cascade narrower">
                              <!-- Card image -->
                              <div class="view view-cascade overlay">
                                  <a action="#" onclick="gogo(${i.p_bun})">
                                    <img class="card-img-top grow" src="./resources/img/${i.picture}">
                                  </a>
                              </div>

                                <!-- Card content -->
                                <div class="card-body card-body-cascade">
                                    <!-- Label -->
                                    <h5 class="pink-text pb-2 pt-1"><i class="fa fa-cutlery"></i> 좋아요  ${i.p_like}개</h5>
                                    <!-- Title -->
                                    <h4 class="font-weight-bold card-title">${i.p_name}</h4>
                                    <!-- Text -->
                                    <p class="card-text">${i.p_title}</p>
                                    <!-- Button -->
                                    <a class="btn btn-main" onclick = "gogo(${i.p_bun})">상세보기</a>
                                </div>
                            </div>
                            <!-- Card Narrower -->
                        </div>
                        <!-- // .col-md-4 -->

                    </c:forEach>

          </div>
          <!-- // .col-md-12 -->

        </div>
        <!-- // #Right -->


      </div>
      <!-- #ConWrap -->

    </div>
    <!-- #Contents -->

  </div>

<form name = "frm1">
    <input type="hidden" name = "p_bun">
</form>
<form name="frm4">
    <input id = "p_name" name = "p_name" type="hidden" value = "anything">
</form>



</body>
</html>
