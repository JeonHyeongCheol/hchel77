

// ProductSelectList ---------------------------- Start ----------------------------

function productList(id) {
	$.ajax({
		type:"get",
		url:"productList",
		data:{"m_id": id},
		dataType:"json",
		success:function(data){
			var list = data.productList;
			var str = "<table class='table text-center table-hover table-striped'>";
			str += "<tr><th class='text-center'>상품번호</th><th class='text-center'>상품명</th><th class='text-center'>즉시구매가</th><th class='text-center'>경매시작가</th><th class='text-center'>입찰가</th><th class='text-center'>판매상태</th><th class='text-center'>입찰마감일</th><th class='text-center'>수정/삭제</th></tr>";
			$(list).each(function(index, obj) {
				str += "<tr>";
				str += "<td><a href='#' onclick = 'gogo(" + obj["p_bun"] + ");'>" + obj["p_bun"] + "</td>";
				str += "<td>" + obj["p_name"] + "</td>";
				str += "<td>" + obj["p_price"] + "</td>";
				str += "<td>" + obj["p_sprice"] + "</td>";
				str += "<td>" + "입찰가" + "</td>";
				// 0일 때 판매 등록중, 1일 때 판매중, 2일때 판매완료  
				if(obj["p_state"] == 0) {
					str += "<td>" + "판매등록 중" + "</td>";
				} else if(obj["p_state"] == 1) {
					str += "<td>" + "입찰 중" + "</td>";
				}
				str += "<td>" + obj["p_edate"] + "</td>";			
				str += "<td><button class='btn btn-warning' onclick='pupdate(" + obj["p_bun"] + ")'>수정</button>&nbsp;&nbsp;<button class='btn btn-danger' onclick='pdelete(" + obj["p_bun"] + ")'>삭제</button></td>"
				str += "</tr>";
			});
			str += "</table>";
			$("#productList").html(str);
		},
		error:function() {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	
	$.ajax({
		type:"get",
		url:"salesProductList",
		data:{"m_id": id},
		dataType:"json",
		success:function(data){
			var list = data.salesProductList;
			var str = "<table class='table table-hover text-center table-striped'>";
			str += "<tr><th class='text-center'>상품번호</th><th class='text-center'>상품명</th><th class='text-center'>판매가</th><th class='text-center'>구매자ID</th><th class='text-center'>판매일</th></tr>";
			$(list).each(function(index, obj) {
				str += "<tr>";
				str += "<td>" + obj["p_bun"] + "</td>";
				str += "<td>" + obj["p_name"] + "</td>";
				str += "<td>" + obj["max"] + "</td>";
				str += "<td>" + obj["t_memid"] + "</td>";
				str += "<td>" + obj["tmax"] + "</td>";
				str += "</tr>";
			});
			str += "</table>";
			$("#salesProductList").html(str);
		},
		error:function() {
			//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}
// ProductSelectList ----------------------------- End -----------------------------

function gogo(i) {
    document.frm1.p_bun.value = i;
    document.frm1.method = "post";
    document.frm1.action = "list_detail";
    document.frm1.submit();
}

$(document).ready(function() {
	$("#nav-upload-tab").on("click", function() {
		$.ajax({
			type:"get",
			url:"category1",
			dataType:"json",
			success:function(data){
				var list = data.category1List;
				var str = "";
				$(list).each(function(index, obj) {
					str +="<option value="+ obj["category_name"] + "' title='" + obj["category_subbun"] +  "'>" + obj["category_name"] + "</option>";
				});
				$("#Categories1").html(str);
			},
			error:function() {
				//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	});
});

function category2() {
	var bun = $("#Categories1 option:selected").attr("title");
	$.ajax({
		type:"get",
		url:"category2",
		data:{"subbun": bun},
		dataType:"json",
		success:function(data){
			var list = data.category2List;
			var str = "";
			$(list).each(function(index, obj) {
				str +="<option value = '"+ obj["category_name"]  + "'>" + obj["category_name"] + "</option>"
			});
			$("#Categories2").html(str);
		},
		error:function() {
			//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function pupdate(u) {
	location.href="pupdate?p_bun=" + u;
}

function pdelete(d) {
	if(confirm("정말로 삭제하시겠습니까?")) {
		location.href="pdelete?p_bun=" + d;
	}
}