package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.MemDaoInter;

@Controller
public class DeleteController {

	@Autowired
	MemDaoInter memDaoInter;
	
	@RequestMapping("delete")
	public String submit(@RequestParam("num") String num) {
		boolean b = memDaoInter.deleteData(num);
		
		if(b) {
			return "redirect:/list";
		} else {
			return "error";
		} 
	}
}
