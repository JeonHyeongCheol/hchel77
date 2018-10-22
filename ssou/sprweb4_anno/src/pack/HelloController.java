package pack;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
	@RequestMapping("hello.do") // get, post 구분하지 않고 다 받음.
	//@RequestMapping(value="hello.do", method=RequestMethod.GET) // get만 받음.
	public ModelAndView kbs() {
		System.out.println("HelloController 처리");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("list1");
		view.addObject("msg", "헬로우");
		return view;
	}
}
