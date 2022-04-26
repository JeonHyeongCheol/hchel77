package pack3;

public class PohamCar {        //완성차 제작 클래스 : 여러 부품(클래스)들로 조립
	int speed = 0;
	String ownerName, turnShow;                          //PohamCar에 고유 맴버들   일반 맴버필드 쓰듯이 자연스럽게 쓴다.
	//.....
	PohamHandle handle;       //클래스를  멤버 필드로 사용
	
	public PohamCar() {
		handle = new PohamHandle(); //클래스의 포함관계
	}
	
	public PohamCar(String name) {
		ownerName = name;
		handle = new PohamHandle(); //클래스의 포함관계
		turnShow = "직진";
	}
	
	public void playHandle(int q) {
		if(q > 0) {
			turnShow = handle.rightTurn(q);
		}else if(q < 0) {
			turnShow = handle.leftTurn(q);
		}else {
			turnShow = handle.straight(0);
		}
	
}
}