package pack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		// 기존 자바의 방법.
		Message1 message1 = new Message1();
		message1.sayHello("홍길동");
		
		Message2 message2 = new Message2();
		message2.sayHello("홍두깨");
		
		MessageInter inter;
		inter = message1;
		inter.sayHello("관우");
		inter = message2;
		inter.sayHello("장비");
		
		System.out.println("-----------아래는 스프링 사용-----------");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("init.xml"); 
		// ClassPathResource 와 비슷. init.xml을 불러와 Bean을 사용.
		// 부가 정보 : ClassPathXmlApplicationContext
		// ClassPathXmlApplicationContext 는 편리한 인스턴스화를 할 수 있도록 다수의 생성자를 노출한다. 
		// 기본 아이디어는 하나만 XML 파일 자체의 파일명을(경로 정보없이) 담고 있는 문자열 배열을 제공하고 Class도 또한 제공한다. 
		// ClassPathXmlApplicationContext는 제공된 클래스에서 경로 정보를 얻을 것이다.
		
		MessageInter inter2 = (MessageInter)context.getBean("mes1"); 
		// context에 올라온 init.xml 안에 Bean Id를 사용. context는 기본적으로 object이기 때문에 캐스팅 해주어야 함.
		inter2.sayHello("손오공");
		inter2 = (MessageInter)context.getBean("mes2");
		inter2.sayHello("저팔계");
	}
	
}
