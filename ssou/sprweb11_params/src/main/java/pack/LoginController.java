package pack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	// params 속성 값에 의해 요청을 구분함.
	@RequestMapping(value = "kic/login", params = "type=admin")
	public ModelAndView kbs() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("showMessage");
		view.addObject("message", "관리자로 로그인");
		
		return view;
	}
	
	@RequestMapping(value = "kic/login", params = "type=user")
	public ModelAndView mbc() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("showMessage");
		view.addObject("message", "일반고객으로 로그인");
		
		return view;
	}
	
	@RequestMapping(value = "kic/login", params = "!type") // 타입 없는 경우
	public ModelAndView sbs() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("showMessage");
		view.addObject("message", "인자가 없어요!!!");
		
		return view;
	}
	
	// login이 아닌 값이 들어게 됨.
	@RequestMapping(value = "kic/{aaa}") // aaa는 경로이기도한데 변수값도 됨. 변수값으로 받아 아래쪽 코드에서 사용 가능.
	public ModelAndView ytn(
			@RequestParam("type") String type, @PathVariable String aaa) { // RequestParam(물음표에 뒤에 들어오는 값들)에서 들어온 type이 String type에 저장됨.
		ModelAndView view = new ModelAndView();
		
		view.setViewName("showMessage");
		view.addObject("message", "type" + type + ", 요청 일부 : " + aaa);
		
		return view;
	}
	
	
	//http://localhost/sprweb11_params/ent/yg/sing/psy?title=챔피언
	@RequestMapping(value = "/ent/{co}/singer/{singer}") // {} 변수값을 설정하여 메소드에서 사용 가능.
	public ModelAndView tvn(
			@RequestParam("title") String title,
			@PathVariable String co,
			@PathVariable String singer) {
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("showMessage");
		String ss = "소속사 : " + co + ", 가수 : " + singer + ", 타이틀 송 : " + title;
		view.addObject("message", ss);
		
		return view;
	}
}
