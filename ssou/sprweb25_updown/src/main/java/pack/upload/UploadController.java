package pack.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	@Autowired
	private FileValidator fileValidator;
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String getUploadform(@ModelAttribute("uploadFile") UploadFile uploadFile) {
		return "uploadform";
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public ModelAndView fileUploadform(@ModelAttribute("uploadFile") UploadFile uploadFile, BindingResult result) {

		return new ModelAndView("uploadFile", "filename", "");
	}
}
