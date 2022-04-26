function Main() {
	location.href="init.jsp";
}

// MemberRegister ---------------------------- Start ----------------------------
$(document).ready(function() {
	$("#m_id").bind("keydown", function(e) {
		var nameReg = /^[가-힣]{3,5}$/g;

			if($("#m_name").val() == "") { 
				alert("이름을 입력하세요");
				return;
			} else if(!nameReg.test($("input[name=m_name]").val())) {
				alert("이름은 한글로 시작해야하며 5자리까지 입력 할 수 있습니다.");
				$("#m_id").val("");
				$("#m_name").val("");
				$("#m_name").focus();
				return;
			}
	});
	
	$("#m_passwd").bind("keydown", function(e) { 

			var idReg = /^[a-z]+[a-z0-9]{3,19}$/g;
			if($("#m_id").val() == "") { 
				alert("ID를 입력하세요");
				return;
			} else if(!idReg.test($("input[name=m_id]").val())) {
				alert("아이디는 영문자로 시작하는 3~20자 영문자 또는 숫자이어야 합니다.");
				$("#m_passwd").val("");
				$("#m_id").val("");
				$("#m_id").focus();
				return;

		}
	});
	
	$("#m_passwd2").bind("keydown", function(e) { 
			var pwdReg = /^(?=.*?[a-zA-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-_]).{8,}$/g;
			
			if($("#m_passwd").val() == "") { 
				alert("비밀번호를 입력하세요");
				return;
			} else if(!pwdReg.test($("input[name=m_passwd]").val())) {
				alert("하나 이상의 숫자 및 대문자와 특수 문자를 포함해 입력하세요");
				$("#m_passwd2").val("");
				$("#m_passwd").val("");
				$("#m_passwd").focus();
				return;
		}
	});
	
	$("#m_phone").bind("keydown", function(e) { 
			var pwd1 = $("#m_passwd").val();
			var pwd2 = $("#m_passwd2").val();
			if($("#m_passwd2").val() == "") { 
				alert("비밀번호확인을 입력하세요");
				return;
			} else if(pwd1 != pwd2) {
				alert("비밀번호가 다릅니다. 다시 입력해 주세요");
				$("#m_phone").val("");
				$("#m_passwd2").val("");
				$("#m_passwd2").focus(); 
				return;
		}
	});
	
	$("#m_email").bind("keydown", function(e) { 
			if($("#m_phone").val() == "") { 
				alert("연락처를 입력하세요");
				$("#m_email").val("");
				$("#m_phone").val("");
				$("#m_phone").focus(); 
				return;
			} 
	});
	
	$("#m_addr1").bind("keydown", function(e) { 
		var mailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			if($("#m_email").val() == "") { 
				alert("이메일을 입력하세요");
				return;	
			} else if(!mailReg.test($("input[name=m_email]").val())) {
				alert("이메일 형식에 맞춰 입력해주세요");
				$("#m_addr1").val("");
				$("#m_email").val("");
				$("#m_email").focus(); 
				return;
			}
	});
	
	
	$("#sub").click(function(e) { 		
		if(confirm("회원가입을 하시겠습니까?")) { 
			submit();
		} 
	});
});

//주소검색창 ---------------------------- Start ----------------------------

function jusosearch() {
	window.open("jusosearch", "주소검색", "width = 600, height = 800 , top = 200, left = 300, scrollbar = no, menubar = no, resizable = no;");
	
   }

// 주소검색창----------------------------- End -----------------------------




// MemberRegister ---------------------------- Start ----------------------------
// MemberRegister ----------------------------- End -----------------------------



// JSP파일명 ---------------------------- Start ----------------------------
// JSP파일명 ----------------------------- End -----------------------------


