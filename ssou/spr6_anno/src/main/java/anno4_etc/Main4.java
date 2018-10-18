package anno4_etc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main4 {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("anno4.xml"); // Abstract로 만들면 Close 가능.
		
		OurProcess ourProcess = (OurProcess)context.getBean("our");
		ourProcess.showResult();
		
		context.close();
	}
}
