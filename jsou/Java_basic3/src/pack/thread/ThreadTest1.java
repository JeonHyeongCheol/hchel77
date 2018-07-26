package pack.thread;

public class ThreadTest1 extends Thread {
	
	public ThreadTest1() {
		
	}
	
	public ThreadTest1(String name) {
		super(name);
	}
	
	public void run() { // 현재 일반메소드 -> extends thread를 클래스 뒤에 써줬을 경우 오버라이딩 되고 있음.
		for (int i = 1; i <= 50; i++) {
			//System.out.print(i + " ");
			System.out.println(i + " : " + super.getName());
		}
	}
	
	public static void main(String[] args) {
		try {
			//프로세서 단위의 처리
			/*
			Process p1 = Runtime.getRuntime().exec("calc.exe");
			Process p2 = Runtime.getRuntime().exec("notepad.exe");
			p1.waitFor(); // 종료하게 되면 같이 열렸던 창들도 같이 닫힘.
			p2.destroy(); // 혼자 종료
			System.out.println("p1 : " + p1.exitValue()); // 정상
			System.out.println("p2 : " + p2.exitValue()); // 비정상종료. p1닫히면서 같이 닫혔기 때문에.
			*/
			
			/*
			ThreadTest1 t1 = new ThreadTest1();
			ThreadTest1 t2 = new ThreadTest1();
			t1.run();
			System.out.println();
			t2.run();
			*/
			
//			ThreadTest1 t1 = new ThreadTest1();
//			ThreadTest1 t2 = new ThreadTest1();
			ThreadTest1 t1 = new ThreadTest1("own");
			ThreadTest1 t2 = new ThreadTest1("two");
			t1.start(); // start() 메소드를 호출하게되면 자동적으로 run() 메소드가 실행됨
			t2.start();
			// 실행하게 되면 t1, t2가 번갈아가면서 실행하게 되는데, 프로세서가 쉬는 시간에 서로 번갈아가면서 실행.
			t2.setPriority(MAX_PRIORITY); // 우선순위 변경 요청
			
			t1.join(); // 일반 스레드의 수행이  끝날때 까지 메인스레드를 대기
			t2.join();
			
			t1.yield(); // 다른 스레드이 수행이 요청되면 스레드의 실행행을 일시정지
			//t1.stop(); //
			
			System.out.println("프로그램 종료");
			
		} catch (Exception e) {
			System.out.println("err" + e);
		}
	}
}
