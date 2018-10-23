package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	private String formViewName = "loginform";
	
//	@Autowired
//	private LoginCommand loginCommand; // setter를 만들어 커맨드 값을 받아옴.
	
	@ModelAttribute("command")
	public LoginCommand formBack() { // logincommand 주소 값 넘기기.
		return new LoginCommand(); // new 사용해도 됨.
	}
	
	@RequestMapping(value="login", method = RequestMethod.GET) // 그냥 index.jsp에서 폼 사용시 get으로 받음.
	public String form() {
		return formViewName;
	}
	
	@RequestMapping(value = "login", method=RequestMethod.POST) // Spring tag 사용시 post로 받음.
	public String submit(LoginCommand loginCommand) { // LoginCommand 클래스 사용(formBean)
		if(loginCommand.getUserid().equals("aa") && loginCommand.getPasswd().equals("11")) {
			return "redirect:list"; // /list로 써도 됨. // 목록보기, redirect 사용.
		} else {
			
			return formViewName; // 입력화면 이동
		}
	}
}
