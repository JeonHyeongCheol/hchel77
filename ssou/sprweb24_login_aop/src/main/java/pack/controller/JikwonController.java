package pack.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.JikwonDto;
import pack.model.JikwonInter;

@Controller
public class JikwonController {
	
	@Autowired
	@Qualifier("jikwonImpl")
	private JikwonInter jikwonInter;
	
	@RequestMapping("jikwonlist")
	public ModelAndView getList(HttpServletRequest request, HttpServletResponse response) { // AOP 사용시 request, response를 꼭 서줘야 함.
		List<JikwonDto> list = jikwonInter.jikwonList();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", list);
		modelAndView.setViewName("jikwonlist");
		return modelAndView;
	}
}
