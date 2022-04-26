package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	private String formViewName = "loginform";
	
	@ModelAttribute("command")
	public LoginCommand formBack() { 
		// 초기화 작업이 필요하다면 작업 진행.
		return new LoginCommand(); 
	}
	
	@RequestMapping(value="login", method = RequestMethod.GET) 
	public String form() {
		return formViewName;
	}
	
	@RequestMapping(value = "login", method=RequestMethod.POST)
	public String submit(
			@Validated @ModelAttribute("command") LoginCommand loginCommand, BindingResult result) { 
		// @Validated(에러 값 확인 후) BindingResult가 에러가 있으면 에러 값 가져옴.
		if(result.hasErrors()) { // 에러값이 있으면 loginform으로 돌 아감.
			return formViewName;
		}
		
		if(loginCommand.getUserid().equals("aa") && loginCommand.getPasswd().equals("11")) {
			
			// 참고 : 컨트롤러 내에서 에러 발생시 출력 파일로 보기
			int a = 10 / 5;
			
			return "redirect:list"; 
		} else {
			
			return formViewName; 
		}
	}
	
	@InitBinder // Bingding과 연결 되어 있음. Web에 있는 값을 가져와 DataVaildator에 넘겨줘 확인 하는 것.
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new DataVaildator());
	}
}
