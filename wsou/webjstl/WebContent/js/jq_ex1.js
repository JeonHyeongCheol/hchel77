$(document).ready(function() {
	var sel = "";
	$("#switcher button").click(function() { 
		switch(this.id) { // id의 값
			case "all" : // 전체를 클릭 했을 경우
				sel = "jq_ex1.jsp";
				break;
			case "ok" : // 직급입력 후 버튼 클릭 했을 경우
				sel = "jq_ex1.jsp?jik="+$("#jik").val()
				break;
		}
		
		$.getJSON(sel, null, function(data) {
			$("#disp").empty();
			var str = "<table border='1px solid black' style='border-collapse:collapse'>";
			str += "<tr><th>번호</th><th>이름</th><th>직급</th><th>부서명</th></tr>";
			//var cou = 0;
			$.each(data, function(kindex, value) {
				str += "<tr><td>" + value["jikwon_no"] + "</td>";
				str += "<td>" + value["jikwon_name"] + "</td>";
				str += "<td>" + value["jikwon_jik"] + "</td>";
				str += "<td>" + value["buser_name"] + "</td>";
				str += "</tr>";
				//cou++;
			});
			$("#disp").append(str);
			str += "</table>";
			//$("#disp").append("인원수 : " + cou + "명"); // 인원 수
			$("#disp").append("인원수 : " + data.length + "명"); // 인원 수
		})
	});
});

