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
	
}