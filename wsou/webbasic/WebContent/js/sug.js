var xhr;
var checkFirst = loopSend = false;
var lastKeyword = "" // 마지막에 들고갈 변수
	
function sijak() {
	//alert("aa");
	if(checkFirst === false) {
		loopSend = true;
		setTimeout("sendKeyword()", 800);
		
	}
}

function sendKeyword() {
	if(loopSend === false) return;
	
	var keyWord = document.frm.keyword.value;
	//console.log(keyword);
	if(keyWord === "") {
		lastKeyword = "";
		hide("suggest")
	} else if(keyWord !== lastKeyword) {
		lastKeyword = keyWord;
		
		if(keyWord !== "") {
			var para = "keyword=" + keyWord;
			xhr = new XMLHttpRequest();
			
			xhr.open("post", "datas/suggest.jsp", true);
			console.log(keyWord);
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4) {
					if(xhr.status === 200) {
						//process();
					} else {
						alert("요청 실패 : " + xhr.status);
					}
				}
			}
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhr.send(para);
		}
	}
}

function hide(ele) {
	var e = document.getElementById(ele);
	if(e) e.style.display = "none"; //안보여주다가
}

function show(ele) {
	var e = document.getElementById(ele);
	if(e) e.style.display = ""; // 보여주다가
}