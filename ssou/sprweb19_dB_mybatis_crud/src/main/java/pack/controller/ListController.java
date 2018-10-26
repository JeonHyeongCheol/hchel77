package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.MemDaoInter;

@Controller
public class ListController {
	
	@Autowired // 하나면 Autowired만 쓰면 됨.ㄴ
	@Qualifier("memDaoImpl") // Interface라 여러가지 클래스가 있을 텐데 그중에 하나를 설정.
	private MemDaoInter memDaoInter;
	
	@RequestMapping("list")
	public ModelAndView list() {
		return new ModelAndView("list", "list", memDaoInter.getDataAll());
	}
}
