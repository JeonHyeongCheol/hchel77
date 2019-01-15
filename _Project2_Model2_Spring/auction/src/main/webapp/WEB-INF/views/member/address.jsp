<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="./resources/css/bootstrap-select.min.css">
<link rel="stylesheet" href="./resources/css/commons.css">
<style>
	.bootstrap-select:not([class*=col-]):not([class*=form-control]):not(.input-group-btn) {
		width: 80%;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./resources/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="./resources/js/address.js"></script>
</head>
<body>

<div class="container-fluid">

	<div class="col-xs-12" style="margin: 4rem 0 2rem 0">

		<div class="col-xs-6 text-center" style="padding: 0;">
			<label for="sido">시도</label>
			<select id="sido" class="form-control" onchange="javascript:addressgungu()">
				<c:forEach var="i" items="${sido}">
					<option value="${i.a2_cdname}">${i.a2_cdname}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-xs-6 text-center" style="padding: 0;">
			<label for="">시군구</label>
			<select id="gungu" class="form-control">
				<option selected="selected">시군구</option>
			</select>
		</div>
	</div>


</div>

		<div class="col-xs-12 form-inline" style="margin-bottom: 2rem;">
			<div class="col-xs-3 text-center">
				<label for="road1" style="margin-top: .8rem;">도로명</label>
			</div>
			<div class="col-xs-9">
				<input type="text" id="road1" name="road1" class="form-control" placeholder="도로명주소를 입력하세요.">
			</div>
		</div>
		<div class="col-xs-12 form-inline" style="margin-bottom: 4rem;">
			<div class="col-xs-3 text-center">
				<label for="road2" style="margin-top: .8rem;">건물번호</label>
			</div>
			<div class="col-xs-9">
				<input type="text" id="road2" name="road2" class="form-control" placeholder="건물번호를 입력하세요.">
			</div>
		</div>

		<div class="col-xs-12">
			<input type="button" id="search" class="form-control btn-info" value="검색">
		</div>

		<!-- Addr view-->
		<div class="col-xs-12">
			<div id="juso"></div>
		</div>

</div>
<!-- //.container-fluid -->

</body>
</html>