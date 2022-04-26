package pack.model;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class HelloModel {
	public String process() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); // date 값을 가져옴.
		
		// date 값에 따라서 return 값이 다름.
		if(hour >= 6 && hour <= 10 ) { 
			return "좋은 아침입니다";
		} else if (hour >= 6 && hour <= 10 ) {
			return "점심 맛있게 드셨나요";
		} else {
			return "안녕하세요";
		}
		
		
	}
}
