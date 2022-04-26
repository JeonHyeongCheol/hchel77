/**
 * 
 */

var yoil;
var nalsu;
var year;
var month;
var day;


function func1() {
	//alert("a");
	var etc = document.getElementById("etc");
	
	var now = new Date();
	year = now.getFullYear();
	month = now.getMonth();
	day = now.getDate();
	
	cal();
	
	if(etc.style.display == "none") {
		etc.style.display = "block";
	}
}

function cal() {
	
	//alert(year + " " + (month + 1) + " " + day);
	
	// 해당 월의 1일은 무슨 요일?
	var setDate = new Date(year, month, 1);
	var firstDay = setDate.getDate();
	//alert(setDate.getDate());
	yoil = setDate.getDay(); // 일 : 0 ~ 토 : 6
	//alert(yoil);
	nalsu = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	if(year % 4 === 0 && year % 100 !==0 || year % 400 === 0) {
		nalsu[1] = 29;
	}
	makeCal(yoil, nalsu[month], year, month + 1, day);
	
	document.getElementById("disp").innerHTML = str;
}

function makeCal(yoil, nalsu, year, month, day) {
	str = "";
	
	console.log(yoil, nalsu, year, month, day);
	str = "<table>";
	str += "<tr><th colspan='7' width='250'>" + year + "년" + month + "월</th></tr>";
	
	str += "<tr>";
	var weeks = new Array("일", "월", "화", "수", "목", "금", "토");
	for(var i=0; i < weeks.length; i++) {
		str += "<th>" + weeks[i] + "</th>";
	}
	str += "</tr>";
	
	// 날 수 채우기
	var no = 1;
	var currentCell = 0;
	var ju = Math.ceil((nalsu + yoil) / 7); // Week 구하기
	//alert("이번달은 몇 주?" + ju);
	for(var r = 0; r < ju; r++) { // 주(행)
		str += "<tr style='text-align:center'>";
		for(var col = 0; col < 7; col++) { // 열
			if(currentCell < yoil || no > nalsu) {
				str += "<td>&nbsp;</td>"
				currentCell++;
			} else {
				if(no === day) {
					str += "<td style='color:blue'>" + no + "</td>"
				} else {
					str += "<td>" + no + "</td>"					
				}
				no++;
			}
		}
		str +="</tr>"
	}

}

function func2() {
	year = year - 1;
	cal();

}

function func3() {
	month = month - 1;
	if(month < 1) {
		year -= 1;
		month += 11;
	}
	cal();

}

function func4() {
	month = month + 1;
	if(11 < month) {
		year += 1;
		month = 0;
	} 
	cal();
}

function func5() {
	year = year + 1;
	cal();

}

