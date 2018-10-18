package anno2_auto;

import org.springframework.stereotype.Component;

@Component // Component는 스스로 객체를 만듬.
public class Sender2 implements SenderInter{
	@Override
	public void show() {
		System.out.println("show 메소드 처리");
		
	}
}
