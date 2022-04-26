package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.MemDaoInter;
import pack.model.MemDto;

@Controller
public class UpdateController {
	
	@Autowired
	MemDaoInter memDaoInter;
	
	@RequestMapping(value="update", method = RequestMethod.GET)
	public ModelAndView form(@RequestParam("num") String num) {
		//MemDto dto = memDaoInter.SelectPart(num); // 이렇게 써준 다음 dto를 넘기면 됨.
		return new ModelAndView("upform", "updata", memDaoInter.SelectPart(num));
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String submit(MemBean bean) {
		boolean b = memDaoInter.updateData(bean);
		if(b) {
			return "redirect:/list";
		} else {
			return "error";
		}
	}
}
