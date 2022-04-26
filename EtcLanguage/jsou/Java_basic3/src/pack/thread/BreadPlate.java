package pack.thread;

public class BreadPlate {
	private int breadCount = 0; // 공유 대상
	
	public BreadPlate() { // 생성자
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void makeBread() {
		if(breadCount >= 10) {
			try {
				System.out.println("빵 생산 초과");
				wait(); // thread 작업을 비활성화
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		breadCount++;
		System.out.println("빵을 생산. 총 " + breadCount + "개");
		notify(); // wait이 꽉 차게되면 wait 되었다가 다시 활성하시키면 되는데 활성 명령어는 notify임.
	}
	
	public synchronized void eatBread() {
		if(breadCount < 1) {
			try {
				System.out.println("빵 소비 불가");
				wait(); // thread 작업을 비활성화
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		breadCount--;
		System.out.println("빵을 소비. 총 " + breadCount + "개");
		notify();
	}
}
