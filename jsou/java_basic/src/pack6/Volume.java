package pack6;

public interface Volume { // interface
	// 추상클래스는 다중상속이 되지 않지만 Interface는 다중상속이 가능.
	void volumeUp(int level); // interface사용시 abstract public을 따로 서주지 않아도 됨. 
	void volumeDown(int level);
	
//	public void aa() {
//		interface는 일반 메소드를 사용 할 수 없음.	
//	}
}
