package anno3_resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main3 {
	
	public static void main(String[] args) {
		String[] metas = {"anno3.xml", "anno3_etc.xml"}; // 먼저 읽고 시작함.
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(metas);
		
		AbcProcess abcProcess = (AbcProcess)context.getBean("abcProcess");
		abcProcess.showData();
		
		
	}
	
	
}
