package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.Jikwon_Buser_Inter;

@Controller
public class ListController {
	
	@Autowired
	private Jikwon_Buser_Inter jikwon_Buser_Inter;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getList(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("allData", jikwon_Buser_Inter.list());
		modelAndView.addObject("selbuser", jikwon_Buser_Inter.blist());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ModelAndView postList(@RequestParam("buser_name") String buser){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("selbuser", jikwon_Buser_Inter.blist());
		if(buser.equals("전체")) {
			modelAndView.addObject("allData", jikwon_Buser_Inter.list());		
		} else {
			modelAndView.addObject("allData", jikwon_Buser_Inter.selectList(buser));
		}
		return modelAndView;
	}
}
