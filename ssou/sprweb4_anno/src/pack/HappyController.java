package pack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HappyController {
	@RequestMapping(value="happy.do") 
	// get으로 하면 못받음. 왜냐하면 줄 때 post이기 때문에. 받을 때 Post로 받으면 Post, get이면 get방식으로 받아 줘야 함. 하지만 둘다 안쓰고 그냥 값만 스면 둘다 받음. 
	public ModelAndView srmn() {
		ModelAndView view = new ModelAndView("list3"); // 이렇게하면 넘어갈 것 없음. viewName만 줌.
		return view;
	}
}
