package pack.thread;

public class BreadEater extends Thread{
	private BreadPlate plate;

	public BreadEater(BreadPlate plate) {
		this.plate = plate;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 30; i ++)
			plate.eatBread(); // 클래스를 변수로 설정하고 변수명.메소드 호출
	}
}
