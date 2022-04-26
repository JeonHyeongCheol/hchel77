package pack5;

public class JepumHandphone extends Jepum {

	@Override
	public void volumeControl() {
		// 오버라이드 강요. 추상클래스이기 때문에 Jepum에 있는 볼륨 컨트롤을 무조건 오버라이드 하여야 함.
		System.out.println("핸드폰 사운드 조정");
	}
	
	@Override
	public void volumeShow() {
		// 오버라이드 선택 
		setVolume(10);
		System.out.println("소리 크기 :" + getVolume());
	}
}
