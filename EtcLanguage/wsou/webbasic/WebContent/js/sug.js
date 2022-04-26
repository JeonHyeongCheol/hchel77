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
			//console.log(keyWord);
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4) {
					if(xhr.status === 200) {
						process();
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

function process() {
	var resultText = xhr.responseText;
	//alert(resultText);
	var result = resultText.split("|");
	//alert("전체 건수 : " + result[0]);
	//alert("전체 이름 : " + result[1]);
	var tot = result[0];
	if(tot > 0) {
		var datas = result[1].split(",");
		var imsi = "";
		for (var i = 0; i < datas.length; i++) {
			imsi += "<a href=\"javascript:func('" + datas[i] + "')\">" + datas[i] + "</a><br>";
		}
		document.getElementById("suggestList").innerHTML = imsi;
		show("suggest");
	}
}

function func(redata) { // 이름 클릭시 선택된 직원명을 넘겨줌. 
	frm.sel.value = redata;
	var checkFirst = loopSend = false;
	var lastKeyword = "";
	hide("suggest");
}

function hide(ele) {
	var e = document.getElementById(ele);
	if(e) e.style.display = "none"; //안보여주다가
}

function show(ele) {
	var e = document.getElementById(ele);
	if(e) e.style.display = ""; // 보여주다가
}