<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./resources/css/commons.css">
<title>login.jsp</title>
<script type="text/javascript">
function func() {
	if(document.loginForm.m_id.value === null || document.loginForm.m_id.value === "") {
		alert("ID를 입력하세요!");
		return;
	} else if(document.loginForm.m_passwd.value === null || document.loginForm.m_passwd.value === "") {
		alert("비밀번호를 입력하세요!");
		return;
	} else {
		loginForm.submit();
	}
}
</script>
</head>
<body>

<div class="container-fluid">















		<div id="Login" class="row col-md-4 col-md-offset-4 box-round">

			<div class="login col-md-12">
				<div class="col-md-12 text-center log-wrap">
					<img src="./resources/img/login.png" alt="login"
						class="log-img">
					<h1>Login to your account</h1><br>
					<form action="login" name="loginForm" method="post">

						<div class="input-group col-md-offset-1 log-width">
							<span class="input-group-addon"> 
								<i class="fa fa-2x fa-user-o fa-fw" aria-hidden="true"></i>
							</span> 
							<input type="text" name="m_id" class="form-control input-height" placeholder="Enter ID Here.">
						</div>

						<div class="input-group col-md-offset-1 log-width">
							<span class="input-group-addon">
								<i class="fa fa-2x fa-unlock-alt fa-fw" aria-hidden="true"></i>
							</span> 
							<input type="password" name="m_passwd" class="form-control input-height" placeholder="Enter Password Here.">
						</div><br>

					</form>
						<div class="col-md-12">
							<div class="col-md-4">
								<input type="button" id="loginc" value="Sign In" onclick="func()" class="btn btn-block btn-primary btn-log">
							</div>

							<div class="col-md-4">
								<input type="button" value="Sign Up" onclick="location.href='memberNew'" class="btn btn-block btn-info" style="float: left;">
							</div>

							<div class="col-md-4">
								<input type="button" value="Main Page" onclick="history.back()" class="btn btn-block btn-warning" style="float: none;">
							</div>
						</div>
					<!-- //form -->

				</div>
			</div>
			<!-- //.login -->




		</div>
		<!-- //#Login -->

</div>

<script src="./resources/js/jquery.com_jquery-3.3.1.slim.js"></script>
<script src="./resources/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./resources/js/login.js"></script>
</body>
</html>
