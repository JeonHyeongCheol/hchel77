package aa.bb.cc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Sub1Controller {
	@RequestMapping(value = "/sub1", method = RequestMethod.GET)
	public ModelAndView home() {
		String msg = "안녕";
		return new ModelAndView("sub_list1", "msg", msg);

	}

}