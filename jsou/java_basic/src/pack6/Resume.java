package pack6;

public interface Resume { // 인터페이스끼리 상속 가능.
	String SIZE = "A4"; // 쓰러지는 글자는 Public Static final이라는 것.
	
	void setIrum(String irum); // public
	void setPhone(String phone);
	void print();
	
	default void playJava(boolean b) { // 자바를 할 수 있는지 없는지
	// java 1.8 부터 인터페이스에서 일반메소드 사용 가능. void만 사용 불가능하기 때문에 앞에 static 이나 default를 사용하여야 함.
		if(b) {
			System.out.println("자바 프로그래밍 가능");
		} else {
			System.out.println("자바 못해");
		}
	}
	
	static void changeData() {
		System.out.println("스태틱 처리 가능");
	}
}
