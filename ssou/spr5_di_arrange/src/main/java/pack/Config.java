package pack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pack2.OutfileInterImpl;

// Annotation 사용
@Configuration
public class Config {
	
	@Bean(name="mImpl")
	public MessageImpl messageImpl() {
		MessageImpl messageImpl = new MessageImpl("유비", "조조", 2000, myInfo());
		messageImpl.setSpec("ADSP 자격증");
		messageImpl.setOutfileInter(outfileInterImpl());
		
		return messageImpl;
	}
	
	@Bean(name="myInfo") // 빈을 만든다는 얘기(객체 생성). Xml Bean이랑 같은 실행을 함.
	public MyInfo myInfo() {
		return new MyInfo();
	}
	
	
	@Bean(name="outfileInterImpl")
	public OutfileInterImpl outfileInterImpl() {
		OutfileInterImpl impl = new OutfileInterImpl();
		impl.setFilepath("C:/work/ssou/output/anno.txt");
		return impl;
	}
	
}
