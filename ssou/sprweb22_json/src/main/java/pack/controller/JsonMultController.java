package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.model.MyModel;

@Controller
@RequestMapping("list2")
public class JsonMultController {
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody // JSP가 없어도 브라우저로 바로 뿌려줌.
	public Map getJsons() {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		
		// Map에 Key, Value로 만들어줌.
		Map<String, String> data = new HashMap<String, String>(); 
		data.put("name", "한사람");
		data.put("age", "22");
		dataList.add(data);
		
		data = new HashMap<String, String>();
		data.put("name", "두사람");
		data.put("age", "33");
		dataList.add(data);
		
		// 추가된 data를 다시 뿌려줘야 함.
		Map<String, Object> data2 = new HashMap<String, Object>();
		data2.put("datas", dataList);
		return data2;
	}
}
