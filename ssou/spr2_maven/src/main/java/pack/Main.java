package pack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("nice.xml");
		
		MessageInter inter = (MessageInter)context.getBean("mes");
		
		inter.sayHello("성공");
	}
}
