package pack6;

public class VolumeMain {
	public static void main(String[] args) {
		//Volume volume = new Volume(); 추상, 인터페이스 이기 때문에 new 사용 불가.
		Volume volume = null; // 객체를 만들 수는 있으나 인스턴스화 할 수 없음.
		
		VolumeRadio radio = new VolumeRadio();
		radio.volumeUp(10);
		radio.volumeDown(5);
		System.out.println();
		VolumeTv tv = new VolumeTv();
		tv.volumeUp(7);
		tv.volumeDown(3);
		tv.kbs();
		
		System.out.println();
		
		volume = radio;
		volume.volumeUp(10);
		volume.volumeDown(8);
		
		System.out.println();
		
		volume = tv;
		volume.volumeUp(10);
		volume.volumeDown(8);
	}
}
