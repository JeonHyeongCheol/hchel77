package pack6;

//public class InterRadio implements interVol ,InterAdvanceVol 
public class InterRadio implements InterAdvanceVol{ // 인터페이스 사용시 다중상속 가능.

	private int v = 0;
	
	@Override // 오버라이드를 빼고 상속 클래스를 지울 시 일반메소드로 바뀜.
	public void volUp(int v) {
		this.v += v;
	}
	
	@Override
	public void volDown(int v) {
		this.v -= v;
	}
	
	@Override
	public void volResume() {
		//System.out.println("소리를 킵니다");
	}
	
	@Override
	public void volOff() {
		//System.out.println("소리를 끕니다");
	}
	
	public void show() {
		System.out.println("현재 볼륨은 " + v);
	}
}
