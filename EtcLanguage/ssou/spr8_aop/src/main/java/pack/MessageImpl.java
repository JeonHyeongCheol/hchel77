package pack;

public class MessageImpl implements MessageInter{
	// 핵심 로직이 운영되는 클래스. - AOP 입장 : Target Class
	
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void sayHi() {
		System.out.println("sayHi!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 처리 완료!!!");
		
	}
}
