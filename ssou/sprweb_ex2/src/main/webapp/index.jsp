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
	$("#sangpum").click(function() {
		$.ajax({
			type:"get",
			url:"list", 
			dataType:"json",
			success:function(data) {
				var list = data.datas;
				
				var str = "<table border='1'>";
				$(list).each(function(index, obj) {
					str += "<tr>";
					str += "<td>" + obj["code"] + "</td>";
					str += "<td>" + obj["sang"] + "</td>";
					str += "<td>" + obj["su"] + "</td>";
					str += "<td>" + obj["dan"] + "</td>";
					str += "</tr>";
				});
				str += "</table>";
				$("#showData").html(str);
			},
			error:function() {
				$("#showData").text("에러!");
			}
		});
	});
});
</script>
</head> 
<body>
<input type="button" value="상품" id="sangpum"></p>
<div id="showData"></div>
</body>
</html>
