package pack6;

public class VolumeRadio implements Volume { // implements : interface 상속 할 때 사용. 
	// 추상클래스와 똑같이 메소드를 override 해줘야 함.
	
	private int volLevel;
	
	public VolumeRadio() {
		volLevel = 0;
	}
	
	@Override
	public void volumeUp(int level) { // 추상 메소드를 일반 메소드로 바꿈.
		volLevel += level;
		System.out.println("라디오 볼륨 올리면 : " + volLevel);
		
	}
	
	@Override
	public void volumeDown(int level) {
		volLevel -= level;
		System.out.println("라디오 볼륨 내리면 : " + volLevel);
		
	}
	
}
