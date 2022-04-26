package aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		String[] configs = new String[] {"initbean1.xml", "initaop1.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(configs);
		
		// AOP는 탈부착 가능(관심 사항, AOP 주요 대상 : login, Security, Transaction)
		WriteInter writeInter = (WriteInter)context.getBean("writeInterImpl");
		writeInter.write();
		HelloInter helloInter = (HelloInter)context.getBean("helloInterImpl");
		helloInter.hello();
		helloInter.hi();
		
		
	}
}
