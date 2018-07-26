package pack2; //성격이 비슷한 클래스들을 모아두는 폴더

//클래스 선언 - 두 개의 멤버를 가질 수 있다
public class Car { // [접근지정자] [기타제한자] calss 클랙스명
	// 접근지정자 : public, private, protected, 생략

	// 멤버필드 (멤버변수, 전역변수)
	int wheel; // [접근지정자] [기타제한자] type 멤버명
	private int airBag = 1; // 캡슐화
	private int speed;
	public String name;

	public Car() {
		// 객체 생성 시 초기화 작업을 함
		// 객체 생성 시 가장 먼저 시스템에 의해 호출
		// 인위적으로 호출 불가
		System.out.println("Car 클래스 생성자");
		wheel = 4;
	}

	// 멤버 메소드
	// [접근지정자] [기타제한자] 반환령 메소드명 (인수, ...){}
	public void abc() {
		System.out.println("abc메소드(method) 수행");
		System.out.println("에어백 수: " + airBag); // private 멤버 호출
		System.out.println("바퀴 수: " + wheel); // default (생략 :friendly)
		def(); // 자신이 속해 있는 클래스 내의 메소드 호출
		int result = korea(7);
		System.out.println("스피커 볼륨은 " + result);
		System.out.println("속도는 " + speed);
		int year = 2018; // 변수 (지역변수)는 초기값 필수
		System.out.println("생산년도 :" + year);
	}

	// 메소드
	// 메소드를 사용하는 이유 : 높은 재사용성, 중복된 코드의 제거, 프로그램의 구조화

	private void def() {
		System.out.println("def 메소드 처리");

	}
	
	// return문
	// void인 경우 반환값이 없어도 되지만 반환타입이 void가 아닌경우 구현부{} 안에 return 반환값;이 반드시 포함되어 있어야 함.
	// 반환타입이 int일 경우 int 값 및 int 값 변수만 가능. if문 등을 사용하여서 참일 경우, 거짓일 경우에 리턴값을 다르게 가능.
	// return에서 연산도 가능.

	int korea(int speaker_vol) {
		int imsi = speaker_vol + 10;
		return imsi;
		
	}

	// 메소드 호출(인자 argument, 매개변수 parameter)
	// 인자, 인수 : 메서드를 호출 할 때 괄호() 안에 지정해준 값들. 인자의 개수, 순서는 호출된 메서드에 선언된 매개변수와 일치해야 함.
	
	// 재귀호출 : 메서드 자신을 다시 호출하는 것을 의미. 재귀호출시 조건문이 필수적으로 해줘야함. 그러지 않을 시 무한루프에 빠지게 됨.
	
	// JVM 메모리 할당
	// 메서드가 호출되면 수행에 필요한 만큼의 메모리를 스택에 할당.
	// 메서드가 수행을 마치고 나면 사용했던 메모리를 반환하고 스택에서 제거.
	// 호출스택의 제일 위에 있는 메서드가 현재 실행 중인 메소드임.
	// 아래에 있는 메서드가 바로 위의 메서드를 호출한 메소드임.

	/*
	 * public int getSpeed() { //private 멤버에 대한 외부 접근 가능 메소드 //getter return speed;
	 * 
	 * }
	 * 
	 * public void setSpeed(int speed) { //setter this.speed = speed;
	 */

	public int getSpeed() { // getter
		return speed;

	}

	public void setSpeed(int password, int speed) { // setter
		if (password == 1234) {
			this.speed = speed;

		} else {
			System.out.println("비밀번호 불일치!!!");
		}
		this.speed = speed;
	}

}
