package anno2_auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main2 {
	public static void main(String[] args) {
		String[] metas = new String[] {"anno2.xml"}; // meta(xml) 파일 많을 경우 넣어서 사용.
		
		ApplicationContext context = new ClassPathXmlApplicationContext(metas[0]);
		
		SenderProcess process = context.getBean("senderProcess", SenderProcess.class);
		process.displaydata();
		process.getSender().show(); // 위에 라인이랑 같은 코드.
		
		
		
	}	
}
