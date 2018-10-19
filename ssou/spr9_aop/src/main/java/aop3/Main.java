package aop3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		String[] configs = new String[] {"initaop3.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(configs);
		
		LogicInter inter = (LogicInter)context.getBean("logicImpl");
		inter.Selectdata_process();
		
		
	}
}
