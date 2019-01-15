package co.kr.auction.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.kr.auction.model.IpchalInter;
import co.kr.auction.model.ProductDaoInter;
import co.kr.auction.model.ProductDto;
import co.kr.auction.model.TenderDto;

@Controller
public class TenderController {

	@Autowired
	@Qualifier("ipchalimpl")
	private IpchalInter ipchalinter;
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDaoInter productDaoInter;
	
	@RequestMapping(value = "ipchal", method = RequestMethod.POST)
	public ModelAndView aopipchal(ProductDto dto, HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		
		TenderDto tdto = new TenderDto();
		tdto.setT_pbun(Integer.toString(dto.getP_bun()));
		tdto.setT_price(dto.getP_sprice());
		
		
		ModelAndView view = new ModelAndView();
		view.addObject("tlist", ipchalinter.alltender(tdto));
		view.addObject("plist", productDaoInter.productDetail(dto.getP_bun()));
		view.setViewName("product/ipchal");
		
		return view;
	}
	
	@RequestMapping("ipchaling")
	public ModelAndView check(TenderDto dto) {
		
		boolean b = ipchalinter.fucking(dto);
		ModelAndView view = new ModelAndView();
		
		
		if(b) { // 입찰 성공
			
			TenderDto tdto = new TenderDto();
			tdto.setT_pbun(dto.getT_pbun());
			tdto.setT_price(dto.getP_sprice());
			view.addObject("tlist", ipchalinter.alltender(tdto));
			view.addObject("plist", productDaoInter.productDetail(Integer.parseInt(dto.getT_pbun())));
			view.setViewName("product/ipchal");

		}else { // 입찰 실패
			view.setViewName("error");
		}
		
		return view;
	}

}
