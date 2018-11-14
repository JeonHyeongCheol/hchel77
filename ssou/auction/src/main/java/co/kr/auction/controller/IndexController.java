package co.kr.auction.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.kr.auction.model.CategoryDto;
import co.kr.auction.model.IpchalInter;
import co.kr.auction.model.ProductDaoInter;
import co.kr.auction.model.ProductDto;

@Controller
public class IndexController {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDaoInter productDaoInter;
	
	@Autowired
	@Qualifier("ipchalimpl")
	private IpchalInter ipchalinter;
	
	// index 상품목록 출력 --------------------------- Start ---------------------------

		@RequestMapping(value = "index", method = RequestMethod.GET)
		public ModelAndView listAll() {
			ModelAndView view = new ModelAndView();
			
			ArrayList<ProductDto> list = (ArrayList<ProductDto>)productDaoInter.productAll(); // 상품목록
			ArrayList<CategoryDto> category = (ArrayList<CategoryDto>)productDaoInter.categorySelect1();
			
			String[] photolist;
			for (int i = 0; i < list.size(); i++) {
				photolist = list.get(i).getP_photo().split("&");
				list.get(i).setPicture(photolist[0]);
				
			}
			
			view.addObject("plist", list);
			view.addObject("clist", category);
			view.setViewName("index");
			return view;
		}

		// index 상품목록 출력 ---------------------------- End ----------------------------
}
