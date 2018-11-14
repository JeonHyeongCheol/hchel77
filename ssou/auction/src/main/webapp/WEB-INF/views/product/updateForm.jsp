<%@page import="co.kr.auction.model.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css">
    <link rel="stylesheet" charset="utf-8" href="./resources/css/summernote.css">
    <link rel="stylesheet" href="./resources/css/commons.css">
    <title>updateForm</title>
    <style>
        .col .menu .hide {display: none;}
        .col-md-2 { padding: 0; }
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


    <!-- Initialize Updateform -->
    <div class="UpdateWrap col-md-12">
        <div class="col-md-offset-3 offsets">

            <div class="col-md-12">
                <i></i>
                <h3>수정페이지 글씨는 예제</h3>
                <small><i class="fa fa-info-circle" aria-hidden="true"></i> ID당 최대 10개까지 등록 가능하며, 90일간 판매됩니다. (경매의 경우 3, 5, 7일 중 선택 가능)</small>
                <br>
                <small><i class="fa fa-info-circle" aria-hidden="true"></i> 등록된 상품은 중고장터를 통해 판매됩니다.</small>
            </div>

            <div class="col-md-12" style="margin-bottom: 3rem;">
                <small><i class="fa fa-info-circle" aria-hidden="true"></i> 판매할 물품의 모델명 또는 브랜드명을 검색하시면 등록 카테고리를 추천해드립니다.</small>
            </div>


            <div class="col-md-12">
                <spring:form enctype="multipart/form-data" modelAttribute="uploading" class="form-inline">
                    <div class="form-group">

                        <div class="col-md-2">
                            <label for="ProductName"><i class="fa fa-quote-left" aria-hidden="true"></i> 상품명</label>
                        </div>
                        <input type="text" name="p_name" id="ProductName" class="form-control" value="${updateData.p_name}" placeholder="상품명을 입력하세요." aria-describedby="ProdHelp" style="width: 50%;">
                        <br><br>

                        <div class="col-md-2">
                            <label for="Title"><i class="fa fa-quote-left" aria-hidden="true"></i> 상품제목</label>
                        </div>
                        <input type="text" name="p_title" id="Title" class="form-control" value="${updateData.p_title}" placeholder="스팸성 키워드 사용을 주의하세요." aria-describedby="ProdHelp" style="width: 50%;">
                        <span id="ProdHelp" class="help-block"> 자가소비 목적으로 해외에서 직구한 상품을 온라인 등을 통해 되파는 경우,<br> 관세법 위반으로 형사처벌 대상이 되오니 유의해주시기 바랍니다. </span>
                        <br>

                        <!-- 카테고리 -->
                        <div id="col-md-12" style="margin-bottom: 2rem; overflow: hidden;">
                            <div class="col-md-2">
                                <label><i class="fa fa-quote-left" aria-hidden="true"></i> 카테고리</label>
                            </div>
                            <div class="col-md-5" style="padding-left: 0;">
                                <select class="form-control" id="Categories1">
                                    
                                </select>
                            </div>
                            <div class="col-md-5" style="padding-left: 0;">
                                <select class="form-control" id="Categories2">
                                    
                                </select>
                            </div>
                        </div>



                        <!-- 해쉬태그 -->
                        <div class="col-md-12" style="padding-left: 0;">
                            <div class="col-md-2" style="margin-bottom: 2rem; overflow: hidden;">
                                <label style="float: left;"><i class="fa fa-quote-left" aria-hidden="true"></i> 태그 수정</label>
                            </div>
                            <div class="col-md-10" style="padding-left: 0;">
                                <input type="text" name="p_tag" class="form-control" value="${updateData.p_tag}" style="width: 80%;" placeholder="태그를  달아주세요."> <br>
                            </div>
                        </div>


                        <div class="col-md-2">
                            <label for="Start"><i class="fa fa-quote-left" aria-hidden="true"></i> 경매 시작가</label>
                        </div>
                        <input type="text" id="Start" name="p_sprice" class="form-control" value="${updateData.p_sprice}">원
                        <br><br>

                        <div class="col-md-2">
                            <label for="Buy"><i class="fa fa-quote-left" aria-hidden="true"></i> 즉시 구매가</label>
                        </div>
                        <input type="text" id="Buy" name="p_price" class="form-control" value="${updateData.p_price}">원
                        <br><br>

                        <div class="col-md-2">
                            <label><i class="fa fa-clock-o" aria-hidden="true"></i> 경매 시작시간</label>
                        </div>
                        <select name="year1" class="select form-control">
                            <option value = "none" selected = "selected">년도</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                        </select>년&nbsp;
                        <select name="month1" class="select form-control">
                            <option value = "none" selected = "selected">월</option>
                            <c:forEach var="i" begin = "1" end = "12" step = "1">
                                <option value = "${i}">${i}</option>
                            </c:forEach>
                        </select>월&nbsp;
                        <select name="date1" class="select form-control">
                            <option value = "none" selected = "selected">일</option>
                            <c:forEach var="i" begin = "1" end = "31" step = "1">
                                <option value = "${i}">${i}</option>
                            </c:forEach>
                        </select>일&nbsp;
                        <select name="time1" class="select form-control">
                            <option value = "none" selected = "selected">시</option>
                            <c:forEach var="i" begin = "1" end = "24" step = "1">
                                <option value = "${i}">${i}</option>
                            </c:forEach>
                        </select>시&nbsp;
                        <select name="min1" class="select form-control">
                            <option value = "none" selected = "selected">분</option>
                            <c:forEach var="i" begin = "0" end = "59" step = "1">
                                <option value = "${i}">${i}</option>
                            </c:forEach>
                        </select>분&nbsp;
                        <br><br>

                        <div class="col-md-2">
                            <label><i class="fa fa-clock-o" aria-hidden="true"></i> 경매 마감시간</label>
                        </div>


                        <select name="year2" class="select form-control">
                            <option value = "none" selected = "selected">년도</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                        </select>년&nbsp;
                        <select name="month2" class="select form-control">
                            <option value = "none" selected = "selected">월</option>
                            <c:forEach var="i" begin = "1" end = "12" step = "1">
                                <option value = "${i}">${i}</option>
                            </c:forEach>
                        </select>월&nbsp;
                        <select name="date2" class="select form-control">
                            <option value = "none" selected = "selected">일</option>
                            <c:forEach var="i" begin = "1" end = "31" step = "1">
                                <option value = "${i}">${i}</option>
                            </c:forEach>
                        </select>일&nbsp;
                        <select name="time2" class="select form-control">
                            <option value = "none" selected = "selected">시</option>
                            <c:forEach var="i" begin = "1" end = "24" step = "1">
                                <option value = "${i}">${i}</option>
                            </c:forEach>
                        </select>시
                        <select name="min2" class="select form-control">
                            <option value = "none" selected = "selected">분</option>
                            <c:forEach var="i" begin = "0" end = "59" step = "1">
                                <option value = "${i}">${i}</option>
                            </c:forEach>
                        </select>분
                        <br>

                        <div class="col-md-2" style="margin-top: 1rem;">
                            <label><i class="fa fa-picture-o" aria-hidden="true"></i> 사진 수정</label>
                        </div>
                        <br><br>

                        <div class="col-md-12">
                            <div class="col-md-6">
                                <div id="fileSelect">
                                    <input class="upload-name" value="파일선택" disabled="disabled">
                                    <label for="show">사진 올리기</label>
                                    <input type="file" id="show" name="file1" class="upload-hidden"><br>
                                </div>
                            </div>
                            <div class="col-md-6"></div>
                            <input type="button" id = "btnadd" class="btn btn-info" value="추가 등록">
                        </div>

                        <br><br>

                        <div class="col-md-2" style="margin: 3rem 0 1rem 0;">
                            <label><i class="fa fa-text-width" aria-hidden="true"></i> 상세설명</label>
                        </div>

                        <!-- Summernote Texteditor -->
                        <div class="col-md-12 editform">
                            <textarea name="p_content" id="summernote" cols="30" rows="10">
                            ${updateData.p_name}
                            </textarea>
                        </div>
                        <!-- // Summernote Texteditor -->

                        <br><br><br>

                        <!-- 번호 : -->
                        <input type="hidden" name="p_bun" value="${updateData.p_bun}">
                        <!-- ID : -->
                        <input type="hidden" name="p_memid" value="${m_id}">
                        <div class="col-md-12" style="margin-bottom: 4rem;">
                            <div class="col-md-6">
                                <input type="submit" value="취소" class="btn btn-danger btn-block">
                            </div>
                            <div class="col-md-6">
                                <input type="submit" value="상품 수정하기" class="btn btn-primary btn-block">
                            </div>
                        </div>
                    </div>
                    <!-- //.form-group -->
                </spring:form>
            </div>
            <!-- //.col-md-12, updateForm ends -->
        </div>
        <!-- //.offsets ends -->
    </div>
    <!-- //.UpdateWrap ends -->
</div>
<!-- //.container -->

<form name = "frm1">
    <input type="hidden" name = "p_bun">
</form>

<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>
<script src="./resources/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>
<script charset="utf-8" src="./resources/js/summernote.js"></script>
<script type="text/javascript" src="./resources/js/product.js"></script>
<script type="text/javascript">
    // Image Upload.
    $(document).ready(function() {
        var cnt = 2;
        $("#btnadd").on("click", function() {
            if(cnt < 5) {
                $("#fileSelect").append("<input class=\"upload-name\" value=\"파일선택\" disabled=\"disabled\"><label for=\"show\">사진 올리기</label><input type='file' id = 'show' name = 'file"+cnt+"'> <br>");
                cnt += 1;
            } else {
                alert("사진 등록은 4장까지만 가능합니다.")
            }
        });
    });

    $(document).ready(function(){
        var fileTarget = $('#fileSelect .upload-hidden');

        fileTarget.on('change', function(){
            if(window.FileReader){
            var filename = $(this)[0].files[0].name;
            } else {
                var filename = $(this).val().split('/').pop().split('\\').pop();
            }
            $(this).siblings('.upload-name').val(filename);
        });
    });

    // Summernote Initialize.
    $(document).ready(function() {
        $('#summernote').summernote({
            lang: 'ko-KR', // default: 'en-US'
            height: 300,
            minHeight: null,
            maxHeight: null,
            //focus: true // set focus to deitable area after initializing summernote.
        });
    });

    // 숨겨진 메뉴
    $(document).ready(function(){

        $(".menu>p").click(function(){

            $(this).next("ul").toggleClass("hide");
        });
    });

    // 두개 선택 불가
    $('input[type="checkbox"][name="p_category"]').click(function(){
        //클릭 이벤트 발생한 요소가 선택 상태일 경우
        //체크된 요소 확인후 복수개 선택되있을 경우 체크 해제
        if ($(this).prop('checked')
            && $('input[type="checkbox"][name="p_category"]:checked').size()>1) {
            $(this).prop('checked', false);
            alert('두개 이상 선택할 수 없습니다.');
        }
    });

    function cate(){
        //if ($('#check_id').is(":checked")) alert("한개의 카테고리만 선택 가능");
        var check = $("input[type='checkbox'][name='p_category']").val();
        alert(check);
    }
</script>
</body>
</html>
