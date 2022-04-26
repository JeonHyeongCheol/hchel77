<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/commons.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <title>경매입찰.jsp</title>
    <style>
        .div-height {
            height: 26px;
        }
    </style>
    <script type="text/javascript">
        function fucksung() {
            if((document.frm1.t_price.value - document.frm2.max.value) <= 0) {
                alert("현재 금액 이상의 금액을 입력해야 합니다.");
                document.frm1.t_price.value = "";
                document.frm1.t_price.focus();
            } else {
                if(confirm("정말 입찰하시겠습니까?")) {
                    document.frm1.action = "ipchaling";
                    document.frm1.method = "post";
                    document.frm1.submit();
                }
            }
        }
        function closing() {
            opener.location.reload();
            window.close();
        }
        
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
    </script>
</head>
<body>

<form name = "frm2">
    <input type="hidden" name = "max" value="${plist.nowprice }">
</form>
<form name = "frm3">
    <input type="hidden" name = "p_bun" value = "${plist.p_bun }">
</form>


<div class="container">

    <div class="col-xs-12">
        <h1><i class="fa fa-gavel" aria-hidden="true"></i> 경매입찰</h1>
    </div>

    <div class="col-xs-12 text-center" style="margin: 3rem 0 3rem 0;">
        <div class="col-xs-12 color-grey div-height">상품명</div>
        <div class="col-xs-12 div-height">${plist.p_name }</div>
        <div class="col-xs-12 color-grey div-height">경매마감일자</div>
        <div class="col-xs-12 div-height">${plist.p_edate }</div>
        <div class="col-xs-12 color-grey div-height">최대 구매가능 수량</div>
        <div class="col-xs-12 div-height">1개</div>
        <div class="col-xs-12 color-grey div-height">판매지역</div>
        <div class="col-xs-12 div-height">전국</div>
    </div>

    <div class="col-xs-12" style="margin-bottom: 3rem;">
        <div class="col-xs-2 color-grey">현재가</div>
        <div class="col-xs-4"><span class="color-red">${plist.nowprice }원</span></div>
        <div class="col-xs-2 color-grey">즉구가</div>
        <div class="col-xs-4" style="margin-bottom: 1rem;">${plist.p_price }원</div>

        <div class="col-xs-12" style="overflow: hidden; padding: 0;">
        <div class="col-xs-2 color-grey">입찰내역<span class="color-red">(${plist.count -1}명)</span></div>
        <div class="col-xs-10">
            <table class="table table-hover table-striped text-center" style="font-size: 10px">
                <tr>
                    <th class="text-center" style="height: 8px;">아이디</th>
                    <th class="text-center">입찰가</th>
                    <th class="text-center">입찰시간</th>
                </tr>
                <c:forEach var = "i" items="${tlist }">
                    <tr>
                        <td>${i.t_memid }</td>
                        <td>${i.t_price }원</td>
                        <td>${i.t_date }</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </div>
        <!-- // wrap .col-xs-12 -->

        <div class="col-xs-2 color-grey">입찰금액</div>

        <div class="col-xs-10">
            <p>현재 <span class="color-red">${plist.nowprice }원</span>부터 입찰하실 수 있습니다.</p>

            <form name = "frm1">
                <input type="hidden" name = "t_pbun" value="${plist.p_bun }">
                <input type="hidden" name = "p_sprice" value="${plist.p_sprice }">
                <input type="hidden" name = "t_memid" value="${m_id}">
                <input type="text" name = "t_price">원
            </form>

            <small>(콤마(",")없이 100원 단위로 입력하세요.)</small><br>
            <span>
            <i class="fa fa-info-circle" aria-hidden="true"></i>
            	<strong>남은 시간</strong>
				<span class="countTimeDay">60</span>일
				<span class="countTimeHour">60</span>시
				<span class="countTimeMinute">60</span>분
				<span class="countTimeSecond">60</span>초
        	</span>
        </div>
        <!-- //.col-xs-10 -->
    </div>

    <div class="col-xs-12">
        <div class="col-xs-6">
            <input type="button" value="입찰하기" class="btn btn-primary btn-block" onclick = "fucksung()">
        </div>
        <div class="col-xs-6">
            <input type="button" value="닫기" class="btn btn-danger btn-block" onclick = "closing()">
        </div>
    </div>

</div>

</body>
</html>
