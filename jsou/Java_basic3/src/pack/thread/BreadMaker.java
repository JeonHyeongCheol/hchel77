package pack.thread;

public class BreadMaker extends Thread {
	private BreadPlate plate;

	public BreadMaker(BreadPlate plate) {
		this.plate = plate;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 30; i ++)
			plate.makeBread(); // 클래스를 변수로 설정하고 변수명.메소드 호출
	}
}
