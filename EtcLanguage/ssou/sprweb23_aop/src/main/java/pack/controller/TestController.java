package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.MyModelInter;

@Controller
public class TestController {
	@Autowired
	@Qualifier("myModel")
	private MyModelInter modelInter;
	
	@RequestMapping("test")
	public ModelAndView abc() {
		String result1 = modelInter.processMsg();
		String result2 = modelInter.businessMsg();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("data1", result1);
		modelAndView.addObject("data2", result2);
		return modelAndView;
	}
}
