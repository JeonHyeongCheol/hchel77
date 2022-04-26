package pack;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XmlController {
	
	@RequestMapping(value="member_xml", method=RequestMethod.GET)
	public String formBack() {
		return "myform";
	}
	
	@RequestMapping(value="member_xml", method=RequestMethod.POST)
	@ResponseBody
	public XmlMessageList aaa(@RequestParam("name") String name, @RequestParam("age") String age) {
		
		return getXml(name,age); // Xml로 받은 값을 다시 되돌려 줌.
	}
	
	public XmlMessageList getXml(String name, String age) {
		List<XmlMessage> messages = Arrays.asList(
				new XmlMessage(name, age),
				new XmlMessage("oscar", "33"),
				new XmlMessage("ellse", "23")
				);
		return new XmlMessageList(messages);
	}
}
