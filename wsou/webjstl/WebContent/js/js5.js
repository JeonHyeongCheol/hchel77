$(document).ready(function() {
	//alert("a");
	var speech = $("div.speech"); // div의 speech의 클래스인 값을 가져오고 
	var defaultSize = speech.css("fontSize"); // speech의 css 폰트 크기를 가져옴.
	//alert(defaultSize);
	//alert(parseFloat('123kbs'));
	
	var num = parseInt(speech.css("fontSize"));
	$("#switcher button").click(function() { // switcher button을 누르게 되면.
		//alert(num);
		switch(this.id) { // id의 값
			case "sw-large" : // large 일 경우
				num += 8; // 증가, num *= 1.4
				break;
			case "sw-small" : // small 일 경우
				num -= 8; // 감소
				break;
			default : // 보통일 경우
				num = parseInt(defaultSize); // 기본 폰트사이즈
		}
		//console.log(num);
		speech.animate({fontSize : num + "px"}, 2000); 
		// animate : 애니메이션, speech에 fontSize를 줌.
	});
	
	$("button, a.more").hover( // hover는 over, out 이벤트가 기본적으로 들어감.
			// button, a태그 class more에 마우스를 올리면.
			function() {
				$(this).addClass("myhover"); // 클래스 추가.
			},
			function() {
				$(this).removeClass("myhover"); // 클래스 제거.
			}
	);
	
	// 문서의 일부 숨기고 보이기
	var para = $("p:eq(1)");
	para.hide(); // 감추기.
	$("a.more").click(function() {
		if(para.is(":hidden")) { // hidden이면
			$(this).text("read less")
			//para.show(); // 글보이기
			//para.fadeIn("slow");
			para.slideToggle("slow");
		} else {
			$(this).text("read more")
			//para.hide();
			//para.fadeOut("slow");
			para.slideToggle("slow");
		}
	});
});