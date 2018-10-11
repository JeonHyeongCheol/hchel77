$(document).ready(function() { // 부서명 클릭 했을 때
	$("#ok").on("click", function() { 
		$.ajax({ 
			type:"post",
			url:"jq_ex2.jsp",
			data:{"flag":"buser", "buser":$("#buser").val()},
			dataType:"xml",
			success:function(data){
				$("#disp1").empty();
				var str = "<table border='1px solid black' style='border-collapse:collapse'>";
				str += "<tr><th>직원번호</th><th>직원명</th><th>부서번호</th></tr>";
				$(data).find("jikwon").each(function(){
					str += "<tr><td>" + $(this).find("bun").text() + "</td>";
					str += "<td><a href=javascript:gogek('"+ $(this).find("bun").text()+"')>" + $(this).find("name").text() + "</a></td>";
					str += "<td>" + $(this).find("no").text() + "</td>";
					str += "</tr>";
				});
				$("#disp1").append(str);
				str += "</table>";
			},
			error:function(){
				$("#disp1").text("에러 발생");
			}
		});
	});
});

function gogek(no) { // 직원명을 눌렀을 때
	$.ajax({ 
		type:"post",
		url:"jq_ex2.jsp",
		data:{"flag":"gogek", "no":no},
		
		dataType:"xml",
		success:function(data){
			$("#disp2").empty();
			var str = "<table border='1px solid black' style='border-collapse:collapse'>";
			str += "<tr><th>고객명</th><th>고객전화</th></tr>";
			$(data).find("gogek").each(function(){
				str += "<tr><td>" + $(this).find("name").text() + "</td>";
				str += "<td>" + $(this).find("tel").text() + "</td>";
				str += "</tr>";
			});
			$("#disp2").append(str);
			str += "</table>";
		},
		error:function(){
			$("#disp2").text("에러 발생");
		}
	});
} 