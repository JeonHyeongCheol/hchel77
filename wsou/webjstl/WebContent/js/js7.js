$(document).ready(function() {
	// 1. xml 읽기
	$("#btn1").on("click", function() {
		//alert("a");
		$.ajax({ // 제일 많이 쓰는 방법
			type:"get",
			url:"jq7_xml1.jsp",
			dataType:"xml",
			success:function(data){
				$("#disp").empty();
				//alert(data);
				var str = "";
				$(data).find("person").each(function(){
					str += $(this).find("irum").text() + " ";
				});
				$("#disp").append(str);
			},
			error:function(){ // 에러 발생시.
				$("#disp").text("에러 발생");
			}
		});
	});
	
	// 2. xml 읽기 - para
	$("#btn2").on("click", function() {
		$.ajax({ 
			type:"post",
			url:"jq7_xml2.jsp",
			//data:"irum=" + "사오정", // 한 개일 경우
			data:{"irum":"삼장법사"},
			//data:{"irum":"삼장법사", "nai":"55"}, // 여러개 일경우
			dataType:"xml",
			success:function(data){
				$("#disp").empty();
				var str = "";
				$(data).find("person").each(function(){
					str += $(this).find("irum").text() + " ";
				});
				$("#disp").append(str);
			},
			error:function(){
				$("#disp").text("에러 발생");
			}
		});
	});
	
	// 3. json 읽기
	$("#btn3").bind("click", function() {
		$.ajax({
			type:"get",
			url:"jq7_json1.jsp",
			dataType:"json",
			success:function(data){
				$("#disp").empty();
				//alert(data);
				var str = "";
				$.each(data, function(index, entry){
					str += entry.name + ", " + entry.age; // 예전에 value값이 아닌 .찍어 사용도 가능. 
				});
				$("#disp").append(str);
			},
			error:function(){
				$("#disp").text("에러발생");
			}
		});
	});
	
	// 4. json 읽기 - para
	$("#btn4").bind("click", function() {
		$.ajax({
			type:"get",
			url:"jq7_json2.jsp",
			data:{"irum":"관우", "nai":"33"},
			dataType:"json",
			success:function(data){
				$("#disp").empty();
				var str = "";
				$.each(data, function(index, entry){
					str += entry["name"] + ", " + entry["age"]; // 예전에 value값이 아닌 .찍어 사용도 가능. 
				});
				$("#disp").append(str);
			},
			error:function(){
				$("#disp").text("에러발생");
			}
		});
	});
});