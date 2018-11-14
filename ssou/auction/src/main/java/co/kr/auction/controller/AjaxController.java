package co.kr.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.auction.model.AjaxInter;
import co.kr.auction.model.XmlContent;

@Controller
public class AjaxController {

	@Autowired
	@Qualifier("ajaxImpl")
	private AjaxInter ajaxInter;
	
	

	@RequestMapping("ajaxgo")
	@ResponseBody
	public XmlContent xmlCon(@RequestParam("baby") String p_name) {
		return makeXml(p_name);
	}

	public XmlContent makeXml(String p_name) {
		List<String> list1 = ajaxInter.getAjax(p_name);		
		return new XmlContent(list1);

	}

	// public Map getJson(@RequestParam("baby") String p_name) {
	//
	// List<String> imsilist = ajaxInter.getAjax(p_name);
	// List<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
	// for (int i = 0; i < imsilist.size(); i++) {
	// Map<String, String> data = new HashMap<String, String>();
	// data.put("name", imsilist.get(i));
	// list1.add(data);
	// }
	// Map<String, Object> list2 = new HashMap<String, Object>();
	// list2.put("key", list1);
	//
	// return list2;
	// }

}
