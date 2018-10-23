package aa.bb.cc;

import java.util.*;
import java.text.DateFormat;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "/home", method = RequestMethod.GET) // home이라고 들어오면..!!
	public String home(Locale locale, Model model) {
		Date date = new Date(); 
		DateFormat dateFormat = 
				DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formatDate = dateFormat.format(date);
		model.addAttribute("serverTime", formatDate); // 값을 줌.
		return "home"; // View Name 줌.
	}

}
