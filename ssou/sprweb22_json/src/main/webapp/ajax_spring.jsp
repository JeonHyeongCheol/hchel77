<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#btn1").click(function() {
		//alert("aa");
		$.ajax({
			type:"get",
			url:"list", // List에 있는 값을 가져옴.
			data:{"name":"oscar"}, // 데이터는 Name을 줌.
			dataType:"json", // 데이터 타입. Xml 인지 json인지
			success:function(data) {
				//alert(data);
				var str = "";
				str += data.name + "<br>";
				str += data.skills[0] + " ";
				str += data.skills[1];
				$("#showData").html(str);
			},
			error:function() {
				$("#showData").text("에러!");
			}
		})
	});
	
	$("#btn2").click(function() {
		//alert("bb");
		$.ajax({
			type:"get",
			url:"list2",
			dataType:"json",
			success:function(data){
				var list = data.datas;
				
				var str = "<table>";
				$(list).each(function(index, obj) {
					str += "<tr>";
					str += "<td>" + obj["name"] + "</td>";
					str += "<td>" + obj["age"] + "</td>";
					str += "</tr>";
				});
				str += "</table>";
				$("#showData").html(str);
			},
			error:function() {
				$("#showData").text("에러!");
			}
		})
	});
});
</script>
</head>
<body>
<h2>Spring이 제공하는 Json 읽기 - Ajax</h2></p>
<!-- View는 외부에서 접속 할 수 없기 때문에 바로 띄울려면 Webapp에서 만들어줘야 함. -->
<input type="button" value="한 개 자료" id="btn1">&nbsp;&nbsp;
<input type="button" value="여러 개 자료" id="btn2"><br>
<div id="showData"></div>
</body>
</html>