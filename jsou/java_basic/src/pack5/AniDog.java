package pack5;

abstract public class AniDog extends Animal { // 부모로서의 추상클래스. 부모클래스에 대해서 다 받지 않아도 됨. 
	// abstract : 미완성의 의미
	// 메서드의 선언부만 작성하고 실제 수행내용은 구현하지 않은 추상 메서드를 선언하는데 사용 됨.
	// abstract가 사용될 수 있는 곳 - 클래스, 메서드
	// 클래스 - 클래스 내에 추상 메서드가 선언되어 있음을 의미.
	// 메서드 - 선언부만 작성하고 구현부는 작성하지 않은 추상 메서드임을 알림.
	@Override
	public String name() {
		return "개";
	}
	
	
}
