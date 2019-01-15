package co.kr.auction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.kr.auction.model.Address2Dto;
import co.kr.auction.model.AddressDaoInter;

@Controller
public class AddressController {
	
	@Autowired
	private AddressDaoInter addressDaoInter;
	
	@RequestMapping(value = "jusosearch", method = RequestMethod.GET)
	public ModelAndView sido() {
		List<Address2Dto> sido = addressDaoInter.city();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("sido", sido);
		modelAndView.setViewName("member/address");
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("sido", sido);
		return modelAndView;
	}
	
	@RequestMapping(value = "gungu", method = RequestMethod.GET)
	@ResponseBody
	public Map gungu(@RequestParam("a_cdname") String city) {
		List<Address2Dto> gungu = addressDaoInter.gu(city);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("gungu", gungu);
		return data;
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public Map search(
			@RequestParam("a_cdname") String sido,
			@RequestParam("a_cggname") String gungu,
			@RequestParam("road1") String road1,
			@RequestParam("road2") String road2) {
		System.out.println(sido + gungu + road1 + road2);
		Address2Bean bean = new Address2Bean();
		bean.setA2_cdname(sido);
		bean.setA2_cggname(gungu);
		bean.setA2_rname(road1 + "%");
		bean.setA2_buildbun(road2 + "%");
		
		List<Address2Dto> search = addressDaoInter.search(bean);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("search", search);
		return data;
	}
	
	
	
	
}
