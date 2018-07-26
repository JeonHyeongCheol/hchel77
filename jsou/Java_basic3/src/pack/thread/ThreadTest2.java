package pack.thread;

public class ThreadTest2 implements Runnable { // 인터페이스 상속
	// Runnable는 start가 없음. run을 상속받아 사용하여야 함.
	public ThreadTest2() {
		// TODO Auto-generated constructor stub
	}
	
	public ThreadTest2(String name) {
		Thread tt = new Thread(this, name);
		tt.start(); // 이거는 스레드이기 때문에 start가 있음.
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println(i + ":" + Thread.currentThread().getName());
			try {
				Thread.sleep(300); // 쉬다가 실행
			} catch (Exception e) {
			// TODO: handle exception
			}	
		}
	}
	
	public static void main(String[] args) {
		new ThreadTest2("하나");
		new ThreadTest2("두울");
		
		System.out.println();
		System.out.println("종료");
	}
}
