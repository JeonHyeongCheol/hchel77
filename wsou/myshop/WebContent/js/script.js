// 회원 가입시 체크
function zipCheck() {
	url = "zipcheck.jsp?check=y"; // 우편찾기 하면 zipcheck을 열고 check에다가 y를 줌.
	window.open(url, "", "toolbar=no, width=450, height=300, top=200, left=300, scrollbar=yes, menubar=no");
}

function idCheck() {
	if(regForm.id.value === "") {
		alert("ID를 입력하세요")
		regForm.id.focus();
	} else {
		url = "idcheck.jsp?id=" + regForm.id.value;
		window.open(url, "", "toolbar=no, width=300, height=150, top=200, left=300, scrollbar=yes, menubar=no");
	}
}

function inputCheck() {
	if(regForm.id.value === "") {
		alert("아이디를 입력하세요");
		regForm.id.focus();
		return;
	}
	
	if(regForm.passwd.value === "") {
		alert("비밀번호를 입력하세요");
		regForm.passwd.focus();
		return;
	}
	
	if(regForm.passwd.value !== regForm.repasswd.value) { // 비밀번호, 비밀번호확인이 틀리면 알림창 뜨게함.
		alert("비밀번호 입력 오류\n 확인하세요");
		regForm.passwd.focus();
		return;
	}
	
	if(regForm.job.value === "0" ) { // 비밀번호, 비밀번호확인이 틀리면 알림창 뜨게함.
		alert("직업을 선택하세요");
		regForm.job.focus();
		return;
	}
	
	// 나머진 생략......
	
	regForm.submit();
}

// 쇼핑몰 로그인 후 자신의 정보 수정시 사용
function memUpdateFunc() {
	//입력자료 검사 생략
	regForm.submit();
}

function memUpdateCancelFunc() {
	location.href="../guest/guest_index.jsp";
}

function memDeleteFunc() {
	alert("회원탈퇴는 불가!!!");
}


// 관리자가 회원수정
function memUpdate(id) {
	alert(id + "님의 정보를 수정합니다");
	document.updateFrm.id.value = id;
	document.updateFrm.submit();
}

function memUpdateAdminFunc() {
	document.updateFormAdmin.submit();
	
}

function memUpdateCancelAdminFunc() {
	location.href = "membermanager.jsp";
}

function productDetail(no) {
	//alert(no);
	document.detailFrm.no.value = no;
	document.detailFrm.submit();
}

function productUpdate(no) {
	//alert(no);
	document.updateFrm.no.value = no;
	document.updateFrm.submit();
}

function productDelete(no) {
	//alert(no);
	if(confirm("정말로 삭제할까요?") == true) {
		document.delFrm.no.value = no;
		document.delFrm.submit();		
	}
}