package pack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.MemberDao;
import pack.model.MemberDto;

@Controller
public class ListController {
	
	private int pageSize = 3; // 각 페이지에 출력 행 수.
	
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping("list") // ~ list?pageNum=1
	public ModelAndView listProcess(@RequestParam(value="pageNum", defaultValue = "1") String pageNum) { 
		// 클라이언트에 값이 넘어올 때 pageNum 값이 넘어옴. 그 값을 java pageNum이 받음.
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize; // 0, 3, 6, 9 .... 행 값 계산.
		int endRow = pageSize;
		int count = memberDao.getMemberCount();
		
		List<MemberDto> list = memberDao.getMemberList(startRow, endRow); // 그 페이지에 맞는 행들만 가져감.
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("list");
		modelAndView.addObject("list", list);
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("pageSize", pageSize);
		modelAndView.addObject("count", count);
		
		return modelAndView;
	}
	
	
}
