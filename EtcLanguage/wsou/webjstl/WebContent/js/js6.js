$(document).ready(function() {
	// 1. html의 text 형태로 읽기
	$("#test1").click(function() {
		//alert("a");
		$("#disp").load("jq6_a.html"); // 일반 텍스트를 읽을 때에는 load를 사용.
	});
	
	// 2. json 읽기
	$("#test2").click(function() {
		$.getJSON("jq6_b.json", null, function(data) { // getJSON : JSON 전문 메소드.
			$("#disp").empty(); // 값 비워주기.
			//alert(data);
			
			$.each(data, function(kindex, value) {
				//alert(kindex + " " + value);
				var str = "<ol>"; // 태그 생성
				str += "<li>" + value["title"] + "</li>";
				str += "<li>" + value["desc"] + "</li>";
				str += "<li>" + value["author"] + "</li>";
				str += "</ol>";
				$("#disp").append(str);
			});
		})
	});
	
	// 3. json 읽기(DB)
	$("#test3").click(function() {
		$.getJSON("jq6_c.jsp", null, function(data) { // getJSON : JSON 전문 메소드.
			$("#disp").empty(); // 값 비워주기.
			//alert(data);
			var items = []; // 배열 생성
			$.each(data, function(kindex, value) {
				//alert(kindex + " " + value);
				var str = "<ol>"; // 태그 생성
				str += "<li>" + value["code"] + "</li>";
				str += "<li>" + value["sang"] + "</li>";
				str += "<li>" + value["su"] + "</li>";
				str += "<li>" + value["dan"] + "</li>";
				str += "</ol>";
				//$("#disp").append(str);
				items.push(str); // 배열에 집어 넣음.
			});
			$("<b/>", {html:items}).appendTo("#disp"); // 배열을 한번에 빼줌.
		})
	});
	
	// 4. xml 읽기
	$("#test4").click(function() {
		$.get("jq6_d.xml", function(data) { // .get() .post() 쓸 수 있음.
			//alert(data);
			//alert($(data).find("aa").size());
			$(data).find("aa").each(function() { // find() : 검색
				var fdata = $(this);
				var str = "<div>";
				str += fdata.attr("part") + " " + fdata.attr("term"); // attr() : 속성 값
				str += " - ";
				str += fdata.find("desc").text(); // 현재 this에 find() 값을 찾아 text()를 읽음.
				str += "</div>";
				$("#disp").append(str);
			});
		});
	});
	
	// 5. xml 읽기(DB)
	$("#test5").click(function() {
		$("#disp").empty();
		$.post("jq6_e.jsp", function(data) {
			//alert(data);
			$(data).find("sangpum").each(function() {
				var sangpum = $(this);
				var str = "<div>"
				str += sangpum.find("code").text() + " ";
				str += sangpum.find("sang").text() + " ";
				str += sangpum.find("su").text() + " ";
				str += sangpum.find("dan").text();
				str += "</div>"
				$("#disp").append(str);
			});
		});
	});
	
});

