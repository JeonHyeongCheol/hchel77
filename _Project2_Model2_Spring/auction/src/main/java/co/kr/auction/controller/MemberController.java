package co.kr.auction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.kr.auction.model.IpchalInter;
import co.kr.auction.model.MemberDaoInter;
import co.kr.auction.model.MemberDto;
import co.kr.auction.model.ProductDto;
import co.kr.auction.model.TenderDto;


@Controller
public class MemberController {
	
	@Autowired
	@Qualifier("memberDaoImpl")
	private MemberDaoInter memberDaoInter;
	
	@Autowired
	@Qualifier("ipchalImpl")
	private IpchalInter ipchalInter;
	
	// 회원 가입 --------------------------- Start ---------------------------
	
	@RequestMapping(value = "memberNew", method = RequestMethod.GET)
	public String memberNewForm(@ModelAttribute("memberBean") MemberBean bean) {
		return "member/memberRegister";
	}
	
	@RequestMapping(value = "memberNew", method = RequestMethod.POST)
	public String memberInsert(MemberBean bean) {
		boolean b = memberDaoInter.memberNew(bean);
		
		if(b) {
			return "redirect:/init.jsp";
		} else {
			return "error";
		}
	}
	
	// 회원 가입 ---------------------------- End ----------------------------
	
	// My Page --------------------------- Start ---------------------------
	@RequestMapping(value = "mypage") 
	public ModelAndView mypage(HttpSession session){
		String id = (String)session.getAttribute("m_id");
		ModelAndView modelAndView = new ModelAndView();
		
		List<TenderDto> tenderPart = ipchalInter.tenderPart(id); // User별 입찰현황
		modelAndView.addObject("tenderPart", tenderPart);
		
		String likes = (String)session.getAttribute("memlike");
		List<TenderDto> tenderPartLike = new ArrayList<TenderDto>();
		
		
		if(likes.contains(",")) {
			String[] likelist = likes.split(",");
			
			for (int i = 0; i < likelist.length; i++) {
				tenderPartLike.add(ipchalInter.tenderPartLike(likelist[i]));
				
			}
		}else {
			tenderPartLike.add(ipchalInter.tenderPartLike(likes));
		}
			
		
		
		
		
		
		
		
		//List<TenderDto> tenderPartLike = ipchalInter.tenderPartLike((String)session.getAttribute("memlike"));
		modelAndView.addObject("tenderPartLike", tenderPartLike);
		modelAndView.setViewName("member/mypage");
		return modelAndView;
	}
	
	// My Page ---------------------------- End ----------------------------
	
	// 회원정보수정 --------------------------- Start ---------------------------
	
	@RequestMapping(value = "memberUpdate", method = RequestMethod.GET) 
	public ModelAndView mUpdate(HttpSession session){
		String id = (String)session.getAttribute("m_id");
		
		MemberDto mUpdateData = memberDaoInter.updateMemPart(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mUpdateData", mUpdateData);
		modelAndView.setViewName("member/memberUpdate");
		return modelAndView;
	}
	
	@RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
	public String memberUpdate(@ModelAttribute("upmemberBean") MemberBean bean) {
		boolean b = memberDaoInter.updateMem(bean);
		
		if(b) {
			return "redirect:/init.jsp";
		} else {
			return "error";
		}
	}
			
	// 회원정보수정 ---------------------------- End ----------------------------
	
	
	
	@RequestMapping(value = "cancling") 
	public ModelAndView mypage1(HttpSession session, @RequestParam("p_bun") String p_bun){
		String id = (String)session.getAttribute("m_id");
		String likes = (String)session.getAttribute("memlike");
		
		
		memberDaoInter.productcancle(p_bun);
		
		
		
		
		
		
		if(likes.contains(",")) {
			String[] likelist = likes.split(",");
			int index = 0;
			String imsi2 = "";
			
			
			
			for (int i = 0; i < likelist.length; i++) {
				if(likelist[i].equals(String.valueOf(p_bun))) {
					index = i;
				}
			}
			
			
			if(index != (likelist.length -1)) {
				String imsi = "";
				imsi = likelist[likelist.length -1];
				likelist[likelist.length -1] = likelist[index];
				likelist[index] = imsi;
			}
			
			for (int i = 0; i < likelist.length-2; i++) {
				imsi2 += likelist[i] + ",";
			}		
			imsi2 += likelist[likelist.length-2];
			likes = imsi2;
			
		} else {
			likes = "";
		}
		MemberBean bean = new MemberBean();
		bean.setM_id(id);
		bean.setM_like(likes);
		memberDaoInter.productmemLike(bean);
		
		
		
		session.removeAttribute("memlike");
		session.setAttribute("memlike", likes);

		
		ModelAndView modelAndView = new ModelAndView();
		
		List<TenderDto> tenderPart = ipchalInter.tenderPart(id); // User별 입찰현황
		modelAndView.addObject("tenderPart", tenderPart);

		List<TenderDto> tenderPartLike = new ArrayList<TenderDto>();
		
		
		if(likes.contains(",")) {
			String[] likelist = likes.split(",");
			
			for (int i = 0; i < likelist.length; i++) {
				tenderPartLike.add(ipchalInter.tenderPartLike(likelist[i]));
				
			}
		}else {
			tenderPartLike.add(ipchalInter.tenderPartLike(likes));
		}
			
		
		
		
		
		
		
		
		//List<TenderDto> tenderPartLike = ipchalInter.tenderPartLike((String)session.getAttribute("memlike"));
		modelAndView.addObject("tenderPartLike", tenderPartLike);
		modelAndView.addObject("tenderPartLike", tenderPartLike);

		modelAndView.setViewName("member/mypage");
		return modelAndView;
	}
	
}



