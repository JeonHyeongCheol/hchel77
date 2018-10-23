package pack;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@Service
@Controller
public class SangpumController {
	
	@Autowired
	private SangpumModel sangpumModel; // 들어오는 값을 모델로 가지고 계산해서 리턴 해줌.
	
	@RequestMapping(value="sangpum", method=RequestMethod.POST) // RequestMapping 값이 sangpum이면 이 메소드를 실행
	//public ModelAndView abc(HttpServletRequest request) {
	public ModelAndView abc(@ModelAttribute("my") SangpumBean bean) { // 들어오는 값이 bean을 가서 값을 주고 값을 받아옴. form에서 넘어오는 값과 Bean이랑 같은 값이여야지 찾아감.
		//String sang = request.getParameter("sang");
		//String su = request.getParameter("su");
		//System.out.println(sang + " " + su);
		System.out.println(bean.getSang() + " " + bean.getSu());
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("showMessage");
		modelAndView.addObject("message", sangpumModel.compute(bean)); // Message라는 값에 good라고 넣어서 보냄
		
		return modelAndView;
	}
	
	@ModelAttribute("ourList") // request.setAttribute("ourList, list ). ModelAttribute를 사용하면 View에서 바로 참조가능. EL에서 별명 속성값을 써서!! ourList가 속성값이 됨!
	public List<String> aaa() {
		List<String> list = new ArrayList<String>();
		list.add("한국인");
		list.add("마당쇠");
		return list;
	}
}
