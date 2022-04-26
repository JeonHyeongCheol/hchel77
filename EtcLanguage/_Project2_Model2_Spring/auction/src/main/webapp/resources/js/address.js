// 시군구 검색 ---------------------------- Start ----------------------------

function addressgungu() {
	var sido = $("#sido option:selected").val();
	$.ajax({
		type:"get",
		url:"gungu",
		data:{"a_cdname": sido},
		dataType:"json",
		success:function(data){
			var list = data.gungu;
			
			var str = "<select id=gungu class='form-control'>"
			$(list).each(function(index, obj) {
				str +="<option value = '"+ obj["a2_cggname"]  + "'>" + obj["a2_cggname"] + "</option>"
			});
			str += "</select>"
			$("#gungu").html(str);
		},
		error:function() {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}
// 시군구 검색 ----------------------------- End -----------------------------

// 주소검색 ---------------------------- Start ----------------------------
$(document).ready(function() { 
	$("#search").bind("click", function addressgungu() {
		var sido = $("#sido option:selected").val();
		var gungu = $("#gungu option:selected").val();
		var road1 = $("#road1").val();
		var road2 = $("#road2").val();
		$.ajax({
			type:"get",
			url:"search",
			data:{"a_cdname": sido, "a_cggname" : gungu, "road1" : road1, "road2" : road2 },
			dataType:"json",
			success:function(data){
				var list = data.search;

				var str = "";
				$(list).each(function(index, obj) {
					str += "<a href=javascript:send('" + obj["a2_cdname"] + "','" + obj["a2_cggname"] + "','" + obj["a2_rname"] + "','" + obj["a2_buildbun"] + "','" + obj["a2_sggbuildname"] + "')>";				 

					str += obj["a2_cdname"] + " ";
					str += obj["a2_cggname"] + " "; 
					str += obj["a2_rname"] + " ";
					str += obj["a2_buildbun"] + " ";
					str += obj["a2_sggbuildname"];
					str += "</a><br>"
					
				});
				
				$("#juso").html(str);
			},
			error:function() {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
});
// 주소검색 ----------------------------- End -----------------------------

function send(a1, a2, a3, a4, a5) {
	opener.document.fr.m_addr1.value = a1 + " " + a2 + " " + a3 + " " + a4 + " " + a5;
	window.close(); // 주소 검색창 닫기
}