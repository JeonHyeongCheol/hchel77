package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.SangpumInter;

@Controller
public class ListContorller {
	
	@Autowired
	private SangpumInter sangpumInter;
	
	@RequestMapping("list")
	public Model process(Model model) {
		model.addAttribute("data", sangpumInter.list());
		return model;
	}
}
