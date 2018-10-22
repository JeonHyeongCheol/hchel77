package pack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController{
	
	// Annotation 사용시, RequestMapping시 index.do가 들어왔을 때 이쪽으로 들어오게 됨.
	@RequestMapping("index.do") 
	public ModelAndView abc() {
		
		System.out.println("TestController 수행");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("say", "스프링 웹 어노테이션!"); // 속성값을 가지고 감.
		return modelAndView;
	}
}
