package pack5;

abstract public class Jepum { // 추상클래스(abstract) : new를 할 수 없음. 객체화 될 수 없음.
	// 추상클래스면 new 할 수 없으며, 메소드가 추상이면 이 클래스는 추상클래스가 되어야 함. 다형성을 위해서 해줌.
	// 추상클래스여도 일반메소드도 만들 수 있음. 자식 클래스가 override 해줘야 함.
	// interface에서도 사용 함.

	// 추상클래스 : 미완성 설계도에 비유, 새로운 클래스를 작성하는데 있어서 바탕이 되는 조상클래스로서 중요한 의미를 가짐.
	// 사용 방법 : 클래스 이름 앞에 abstract를 붙임.
	
	// 추상메서드(abstract method) : 선언부, 구현부(몸통)으로 구성, 선언부는 작성을하고 구현부는 작성하지 않고 남겨 두는 것.
	// 사용 방법 : ex) abstract void stop(), abstract void play(int pos)
	// 추상클래스로부터 상속받는 자손 클래스는 반드시 메서드를 다 구현 하여야 하지만, 그렇지 않을 경우 자손 클래스도 추상클래스 정의 하면 가능.
	
	// 상속과 추상에 차이점
	// 상속 : 자손 클래스를 만드는데 조상 클래스를 사용.
	// 추상 : 기존의 클래스의 공통부분을 뽑아내서 조상 클래스를 만드는 것.
	
	// 추상화 : 클래스간의 공통점을 찾아내서 공통의 조상을 만드는 작업.
	// 구체화 : 상속을 통해 클래스를 구현, 확장하는 작업.
	
	// 여러 종류의 객체를 배열로 다루기
	// ex) Product[] item = new Product[10]; <- 이렇게하여 객체를 배열로 선언 가능.
	
	private int volume = 0;
	
	public Jepum() {
		System.out.println("추상 클래스 생성자");
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public int getVolume() {
		return volume;
	}
	
	
	public void volumeShow() {
		System.out.println("소리 크기는 " + volume);
	}
	
	// 추상메소드 : 자식을 거닐고 있는 메소드, 일반클래스로 될 수 없는 추상클래스로 만들어 줘야함.
	abstract public void volumeControl(); // body({})가 없는 메소드.
	
}
