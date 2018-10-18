package anno1_required;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main1 {
	public static void main(String[] args) {
		AbstractApplicationContext context= new ClassPathXmlApplicationContext("anno1.xml"); // 추상화
		
		Abc abc = context.getBean("abc", Abc.class);
		
		System.out.println(abc);
		
		context.registerShutdownHook(); // 웹 관련. Servlet Destory()를 명시적으로 호출. Destory()는 웹 서버 종료시 사용하는 메소드는.
		context.refresh();
		context.close();
	}
}
