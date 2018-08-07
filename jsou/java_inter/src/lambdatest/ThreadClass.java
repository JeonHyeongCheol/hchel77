package lambdatest;

import javax.sound.midi.Track;

public class ThreadClass {

	public void sendEmail(String ss) {
		System.out.println(ss + " 메일을 전송");
	}
	
	public ThreadClass() {
		m1();
		m2();
		m3();
		m4();
	}
	
	void m1() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				sendEmail("m1");
			}
		}).start();
	}
	
	void m2() { // 람다 사용
		Thread thread = new Thread(() -> sendEmail("m2"));
		thread.start();
	}
	
	void m3() { // 람다 사용2
		new Thread(() -> sendEmail("m3")).start();
	}
	
	void m4() { // 람다 사용3
		Runnable runnable = () -> sendEmail("m4");
		runnable.run();
		// Thread 안에서 Runnable을 부르지만 여기서는 Runnable을 불러서 바로 사용 가능
		// 하지만 Runnable 객체를 선언해서 사용 할 수는 없음.
	}
	
	public static void main(String[] args) {
		new ThreadClass();

	}

}
