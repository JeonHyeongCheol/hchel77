package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.MemDaoInter;

@Controller
public class InsertController {
	@Autowired
	private MemDaoInter memDaoInter;
	
	@RequestMapping(value="insert", method = RequestMethod.GET)
	public String form() {
		return "insform";
	}
	
	@RequestMapping(value ="insert", method = RequestMethod.POST)
	public String submit(MemBean bean) {
		boolean b = memDaoInter.insertData(bean);
		if(b) {
			return "redirect:/list"; // Insert 서버로 끌고 오는게 아니기 때문에 redirect로 List로 가야 함.
		} else {
			return "error"; // 이 페이지에서 error를 알려줘야 함.
		}
	}
}
