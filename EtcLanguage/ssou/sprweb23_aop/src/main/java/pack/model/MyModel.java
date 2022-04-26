package pack.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MyModel implements MyModelInter{
	
	@Override
	public String processMsg() {
		System.out.println("processMsg 메소드 수행"); 
		return "Spirng AOP 만세";
	}
	
	@Override
	public String businessMsg() {
		System.out.println("businessMsg 메소드 수행");
		return "Spring AOP 나이스";
	}
}
