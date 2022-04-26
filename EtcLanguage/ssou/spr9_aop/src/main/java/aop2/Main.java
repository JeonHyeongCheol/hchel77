package aop2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		String[] configs = new String[] {"initaop2.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(configs);
		
		LogicInter inter = (LogicInter)context.getBean("logicImpl");
		inter.Selectdata_process();
		
		
	}
}
