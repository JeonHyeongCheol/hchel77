<%@page import="co.kr.auction.model.ProductDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    ProductDto dto = (ProductDto)request.getAttribute("plist");
    String pictures = dto.getP_photo();
    String[] picture = pictures.split("&");
    
    
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<link rel="stylesheet" href="./resources/css/xzoom.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./resources/css/commons.css">

<!-- javascript -->
<script src="./resources/js/jquery-3.3.1.min.js"></script>
<script
  src="https://code.jquery.com/jquery-1.9.1.js"
  integrity="sha256-e9gNBsAcA0DBuRWbm0oZfbiCyhjLrI6bmqAl5o+ZjUA="
  crossorigin="anonymous"></script>
<script src="./resources/js/bootstrap.min.js"></script>
<script src="./resources/js/component.js"></script>
<script src="./resources/js/xzoom.min.js"></script>
<script id="dsq-count-scr" src="//disqustest-dudjva8yv1.disqus.com/count.js" async></script>

<title>list_detail.jsp</title>

    <script type="text/javascript">
        $(document).ready(function(){
            //초기값
            var enddate = '${plist.p_edate}';

            var nowtime = new Date();
            var endtime = new Date(enddate);

            var gap = endtime.getTime() - nowtime.getTime();

            var day = Math.floor(gap/1000/60/60/24);
            var hour = Math.floor(gap/1000/60/60) - (24 * day);
            var minute = Math.floor(gap/1000/60) - (60 * hour) - (60 * 24 * day);
            var second = Math.floor(gap/1000) - (60 * minute) - (60 * 60 * hour) - (60 * 60 * 24 * day);

            console.log((hour), minute, second);

            // 초기화
            $(".countTimeDay").html(day);
            $(".countTimeHour").html(hour);
            $(".countTimeMinute").html(minute);
            $(".countTimeSecond").html(second);

            var timer = setInterval(function () {
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


            }, 1000); /* millisecond 단위의 인터벌 */
        });

        function fuckchang() {
            window.open("", "gg", "width = 600, height = 700 , top = 200, left = 300, scrollbar = no, menubar = no, resizable = no;");

            document.frm1.action = "ipchal";
            document.frm1.target = "gg";
            document.frm1.method = "post";
            document.frm1.submit();

        }

        function buynow() {
            window.open("", "buy", "width = 600, height = 700 , top = 200, left = 300, scrollbar = no, menubar = no, resizable = no;");

            document.frm1.action =	"";
            document.frm1.target = "buy";
            document.frm1.method = "post";
            document.frm1.submit();

        }

        function liking() {
            document.frm1.action = "liking";
            document.frm1.method = "post";
            document.frm1.submit();
        }
    </script>

</head>
<body>
<form name="frm1">
    <input type="hidden" name="p_bun" value="${plist.p_bun}">
    <input type="hidden" name="p_sprice" value="${plist.p_sprice}">
</form>

<div class="container-fluid">

		<div class="col-md-12 nav-wrap">

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
								<li><a href="mypage">My Page</a></li>
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
		<!-- // .nav-wrap -->


		<div class="col-md-12" style="margin-top: 8rem;">
			<div class="col-md-6 left-content">
				<div class="col-md-12">

					<div class="col-md-12">
					
						<!-- main image -->
						<img src="./resources/img/<%=picture[0] %>" id="main_image"
							class="xzoom gallery-image-main" xoriginal="./resources/img/<%=picture[0] %>">
					</div>

					<!-- tumbnails -->
					<div class="xzoom-thumbs">
						<c:forEach var = "i" items="<%= picture %>">
							<div class="col-md-3 img-mg">
								<a href="./resources/img/${i }">
									<img src="./resources/img/${i }" class="xzoom-gallery gallery-image xactive"
									xpreview="./resources/img/${i }">
								</a>
							</div>
						</c:forEach>
					</div>
					<!-- //.xzoom-thumbs -->
				</div>
				<!-- //.col-md-12 (left-content wrap) -->
			</div>
			<!-- //.left-content -->


			<div class="col-md-6 right-content">
				<div class="col-md-12">
					<span>상품명 : </span><h1>${plist.p_name}</h1>
				</div>
				<div class="col-md-12" style="border-bottom: 2px solid #b3b3b3;">
					<div class="col-md-2">
						<span><i class="fa fa-money" aria-hidden="true"></i> 현재가</span>
					</div>
					<div class="col-md-4" style="margin: 0;">
						<h3 class="text-center">
							<strong class="color-red">${plist.nowprice} 원</strong>
						</h3>
					</div>
					<div class="col-md-6">
						<span>시작가 ${plist.p_sprice}</span>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-2">
						<span><i class="fa fa-users" aria-hidden="true"></i> 입찰수</span>
					</div>
					<div class="col-md-10">
						<span>${plist.count -1 }회 (총 판매수량 : 1개)</span>
					</div>
					<div class="col-md-2">
						<span><i class="fa fa-clock-o" aria-hidden="true"></i> 남은시간</span>
					</div>
					<div class="col-md-10">
						<strong>남은 시간</strong>
						<span class="countTimeDay">60</span>일
						<span class="countTimeHour">60</span>시
						<span class="countTimeMinute">60</span>분
						<span class="countTimeSecond">60</span>초
						<span id = "edate">(종료 : ${plist.p_edate })</span>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<span><i class="fa fa-truck" aria-hidden="true"></i> 배송방법</span>
					</div>
					<div class="col-md-10">
						<span>택배</span>
					</div>
					<div class="col-md-2">
						<span><i class="fa fa-credit-card-alt" aria-hidden="true"></i> 배송비</span>
					</div>
					<div class="col-md-10">
						<span>후결제 3,000원</span>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 3rem;">
					<div class="col-md-8 col-md-offset-4">
						<div class="col-md-4">
							<input type="button" value="입찰하기" class="btn btn-primary btn-block"
								onclick="fuckchang()">
						</div>
						<div class="col-md-4">
							<input type="button" value="바로구매하기" class="btn btn-primary btn-block"
								onclick="buynow()">
						</div>
						<div class="col-md-4">
							<input type="button" value="관심상품등록" class="btn btn-primary btn-block"
							    onclick="liking();">
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- //.right-content -->



		<!-- nav-tab -->
		<div class="col-md-12" style="margin-bottom: 4rem;">

			<nav class="sub-nav" style="margin-bottom: 2rem;">
				<ul class="nav nav-pills" id="nav-tab" role="tablist">
					<li class="nav-item nav-link btn on-color" id="nav-home-tab"
						data-toggle="tab" href="#nav-home" role="tab"
						aria-controls="nav-home" aria-selected="true">상품정보</li> 
					<li class="nav-item nav-link btn on-color" id="nav-upload-tab" data-toggle="tab"
						href="#nav-upload" role="tab" aria-controls="nav-upload"
						aria-selected="false">상품문의</li>
				</ul>
			</nav>
			<!-- // navigation -->


			<div class="tab-content" id="nav-tabContent">

				<div class="tab-pane fade active" id="nav-home" role="tabpanel"
					aria-labelledby="nav-home-tab">

					<!-- 상품 정보1 -->


					<!-- 상품 정보 2-->
					<table class="table text-center">
						<tr>
							<th class="text-center">물품상태</th>
							<th class="text-center">판매지역</th>
							<th class="text-center">판매자 거주지역</th>
						</tr>
						<tr>
							<td>S급</td>
							<td>강남구</td>
							<td>서울시 강남구 삼성동</td>
						</tr>
					</table>

					<div class="col-md-12">
						<small><i class="fa fa-exclamation" aria-hidden="true"></i>
							결제 시스템을 이용하지 않고 <strong>판매자의 계좌로 상품대금을 송금</strong>하는 등의 방법으로 <strong>직거래</strong>를
							하는 경우, 상품을 받지 못하는 등의 <strong>피해가 발생</strong>할 수 있습니다. <br>
						<i class="fa fa-exclamation" aria-hidden="true"></i> 직거래로 인한 피해
							발생시, <strong>KIC는 일체의 책임을 지지 않습니다.</strong></small>
					</div>
					<br>
					<br>
					<div class="col-md-12">
						<small><i class="fa fa-exclamation" aria-hidden="true"></i>
							품명 및 모델명/소재,순도,밴드재질/중량/사이즈,치수/주요사양/가공방법/안전인증 또는 검사필
							여부(해당품목)/기능성(성적서 또는 허가서 첨부)/제조년월/제조자, 수입품의 경우 수입자 또는 판매자/제조국,수입품의
							경우 정품,병행수입 여부/착용 또는 이용시 피부 부작용 주의문구/품질보증기간/AS관련 전화번호</small>
					</div>



				</div>
				<!-- //#nav-home -->


				<!-- disqus -->
				<div class="tab-pane fade" id="nav-upload" role="tabpanel"
					aria-labelledby="nav-upload-tab">
					<div id="disqus_thread"></div>
				</div>
				<!-- //#nav-upload -->

			</div>
			<!-- //.tab-content #nav-tabContent -->
		</div>


	</div>


<script>
$(document).ready(function() {
	$('.nav-pills li:first').tab('show');
});
/**
*  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
*  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
/*
var disqus_config = function () {
this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
};
*/
(function() { // DON'T EDIT BELOW THIS LINE
	var d = document, s = d.createElement('script');
	s.src = 'https://disqustest-dudjva8yv1.disqus.com/embed.js';
	s.setAttribute('data-timestamp', + new Date());
	(d.head || d.body).appendChild(s);
})();
</script>
<noscript>
	Please enable JavaScript to view the
	<a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a>
</noscript>
<script>
$(document).ready(function() {
	$(".xzoom, .xzoom-gallery").xzoom({
position: 'inside', // top, left, right, bottom, inside, lens, fullscreen, #ID
mposition: 'fullscreen', // inside, fullscreen
// In the HTML structure, this option gives an ability to output xzoom element, to the end of the document body or relative to the parent element of main source image.
rootOutput: true,

// x/y offset
Xoffset: 0,
Yoffset: 0,
fadeIn: true, // Fade in effect, when zoom is opening.
fadeTrans: true, // Fade transition effect, when switching images by clicking on thumbnails.
smooth: true, // Enable smooth effects

// Smooth move effect of the big zoomed image in the zoom output window.
// The higher value will make movement smoother.
smoothZoomMove: 3,
smoothLensMove: 1, // Smooth move effect of lens.
smoothScale: 6, // Smooth move effect of scale.
defaultScale: 0, // From -1 to 1, that means -100% till 100% scale
scroll: true, // Scale on mouse scroll.
tintOpacity: 0.5, // Tint opacity from 0 to 1.
lensOpacity: 0.5, // Lens opacity from 0 to 1.
lensShape: 'box', // 'box', 'circle'
zoomWidth: 'auto', // Custom width of zoom window in pixels.
zoomHeight: 'auto', // Custom height of zoom window in pixels.
sourceClass: 'xzoom-source', // Class name for source "div" container.

// Class name for loading "div" container that appear before zoom opens, when image is still loading.
loadingClass: 'xzoom-loading',
lensClass: 'xzoom-lens', // Class name for lens "div".
zoomClass: 'xzoom-preview', // Class name for zoom window(div).
activeClass: 'xactive', // Class name that will be added to active thumbnail image.
hover: false, // With this option you can make a selection action on thumbnail by hover mouse point on it.
adaptive: true, // Adaptive functionality.

// When selected position "inside" and this option is set to true, the lens direction of moving will be reversed.
lensReverse: false,

// Same as lensReverse, but only available when adaptive is true.
adaptiveReverse: false,

// Lens will collide and not go out of main image borders. This option is always false for position "lens".
lensCollision: true,

title: false, //  Output title/caption of the image, in the zoom output window.
titleClass: 'xzoom-caption', // Class name for caption "div" container.
bg: false // Zoom image output as background
    });
});
</script>
</body>
</html>
