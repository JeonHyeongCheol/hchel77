package pack6;

abstract public class FlyAdapter implements Flyer { // 어댑터 만드는 방법. 추상클래스 명령어(abstract) 입력
	// 인터페이스를 상속 받고 이 클래스에서는 상속 명령어를 입력하고 상속메소드에 블럭을 해놓으면 다음 클래스에서 일반메소드처럼 사용 가능.
	// 인터페이스에서 인터페이스를 implements해서 받을 수는 없고 extends 하여야지 상속 가능.
	// 추상메소드, 인터페이스는 public 일반 메소드 사용 불가능, static, default는 사용 가능(Java 1.8부터)
	
	@Override
	public void fly() {} // 이렇게 블럭을 줄임. 그럼 일반 메소드가 됨.
	
	@Override
	public boolean isAnimal() {
		return false;
	}

}
