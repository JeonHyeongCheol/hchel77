package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.model.MyModel;

@Controller
@RequestMapping("list")
public class JsonController {
	
	@Autowired
	private MyModel myModel;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public MyModel getJson(@RequestParam("name") String name) { // Json은 그냥 responseBody하면 자동으로 만들어줌.
		myModel.setSkills(new String[] {"자바 갤발자", "웹프로그래머"});
		myModel.setName(name);
		return myModel;
	}
}
