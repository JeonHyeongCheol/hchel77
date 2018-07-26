package pack6;

public class VolumeTv implements Volume {
	
	private int vol;
	
	public VolumeTv() {
		vol = 0;
	}
	
	@Override
	public void volumeUp(int level) {
		System.out.println("Tv 소리 키워 : " + level);
	}
	
	@Override
	public void volumeDown(int level) {
		System.out.println("Tv 소리 줄여 : " + level);
	}
	
	public void kbs() {
		System.out.println("국영방송 9");
	}

}
