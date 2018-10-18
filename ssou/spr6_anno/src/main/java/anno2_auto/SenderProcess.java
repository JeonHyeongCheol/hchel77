package anno2_auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
//@Component("senderProcess") // 생성시 이렇게 ID 설정. 다른 이름 설정하고 싶으면 변경하면됨.
@Service // 비즈니스 로직이 실행되는 곳을 확인시켜주기위해 사용.(가독성을 높이기 위해). 원래는 Component가 맞음.
public class SenderProcess {

//	@Autowired // Type에 의한 Mapping. 내부적으로 Setter가 진행. Setter를 따로 만들어 줄 필요가 없음.
//	private Sender sender;
	
	// 위에는 그 클래스에 대한 객체 생성, 밑에는 부모를 객체로 생성.
	@Autowired // Autowired 와 Qualifier와 함께 써야 함. 
	@Qualifier("sender") // 아이디를 입력하면 그 아이디를 찾아 맵핑.
	private SenderInter senderInter; // Inter(부모)를 객체로 달아 놨음.
	
//	public void setSender(Sender sender) {
//		this.sender = sender;
//	}
	
//	public Sender getSender() {
//		return sender;
//	}
	
	public SenderInter getSender() {
		return senderInter;
	}

	public void displaydata() {
		senderInter.show(); // 얘가 senderInter안에 자식이 누군지 알 수 없음.
	}
}
