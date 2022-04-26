
package pack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ArrangeMain {
	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:arrange.xml"); // 원래 경로 쓸 때는 classpath:를 써야 함.
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath:pack/arrange.xml"); // arrange.xml이 pack 와 있을 경우 이렇게 써줘야 함.
		//GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:arrange.xml"); // 낮은 버전에 사용 못함.
		
		
		//GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:arrange.xml"); // 바이트 코드를 직접 참여 시킴.(클래스에는 Configuration Annotation을 사용 하여야 가능)
		
		//Anonotation File로 환경 설정 시
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		
		// Singleton 여부 확인
		MessageImpl impl1 = (MessageImpl)context.getBean("mImpl");
		impl1.sayHi();
		
		MessageImpl impl2 = (MessageImpl)context.getBean("mImpl");
		impl2.sayHi();
		
		System.out.println(impl1.toString());
		System.out.println(impl2.toString());
		
		System.out.println("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		// 다형성
		//MessageInter inter = (MessageImpl)context.getBean("mImpl"); // 파생 클래스가 적을 때는 써야되는 클래스로 캐스팅 함.
		//MessageInter inter = (MessageInter)context.getBean("mImpl"); // 파생 클래스가 많을 때는 인터페이스로 캐스팅 해야지 바꾸지 않고 사용 할 수 있음.
		//MessageInter inter = context.getBean("mImpl", MessageImpl.class); // (적을 때)타입을 이렇게 변수값으로 줄 수 있음.
		MessageInter inter = context.getBean("mImpl", MessageInter.class); // (많을 때)타입을 이렇게 변수값으로 줄 수 있음.
		inter.sayHi();
		
		context.close(); // 자원 관리하기 위해 닫음. ApplicationContext는 버전이 낮아 close 하지 못함. GenericXml는 업그레이드 된 버전이여서 가능.
		
	}
}
