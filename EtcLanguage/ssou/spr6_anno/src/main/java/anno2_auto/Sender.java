package anno2_auto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component // Component는 기본적으로 싱글톤 임.
//@Component("sender") // Component는 스스로 객체를 만듬. 필요시. ID설정 가능(기본적으로 클래스 이름의 앞에 소문자로 변경하여 만듬).
//@Component("my1")
//@Scope("prototype") // 만들 때마다 객체를 만들경우 Scope 변경하면 됨.
public class Sender implements SenderInter{
	public void show() {
		System.out.println("show 메소드 수행");
	}
}
