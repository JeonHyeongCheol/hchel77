package pack.thread;

public class ThreadTest1 extends Thread {
	// Thread : 프로세스의 자원을 이용해서 실제로 작업을 수행하는 것. 하나씩 처리하지만 여러 작업들을 번갈아 수행하여 모두 동시 수행되는 것 처럼 보임.
	
	// 멀티태스킹과 멀티쓰레딩
	// 멀티태스킹 : 여러 개의 프로세스가 동시 실행
	// 멀티쓰레딩 : 하나의 프로세스 내에서 여러 쓰레드가 동시에 작업을 수행하는 것.
	
	// 멀티쓰레딩의 장단점
	// 장점 
	// 1. CPU의 사용률을 향상.
	// 2. 자원을보다 효율적으로 사용.
	// 3. 사용자에 대한 응답성이 향상.
	// 4. 작업이 분리되어 코드가 간결.
	// 단점
	// 같은 프로세스 내에 자원을 공유하면서 작업을 함. -> 동기화(synchronization), 교착상태(DeadLock)와 같은 문제들 고려해야 함.
	
	// 쓰레드의 구현과 실행
	// 1. Thread클래스를 상속
	// 	   쓰레드의 실행 - Start()를 호출해야 쓰레드 실행.
	// 2. Runnable인터페이스를 구현 : Run을 오버로드 해야함. 
	//    Runnable 구현시 Thread 클래스의 static메서드인 current Thread()를 호출하여 쓰레드에 대한 참조를 얻어야지 호출 가능.
	//	  static Thread currentThread() : 현재 실행중인 쓰레드의 참조를 반환함.
	//	  String getName() : 쓰레드의 이름을 반환.

	// start()와 run()
	// start() : 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택을 생성한 다음 Run()을 호출해, 생성된 호출스택 run()이 첫 번째로 올라감.
	// run() : 생성된 쓰레드를 실행 시키는 것이 아닌 단순히 클래스에 선언된 메서드를 호출 하는 것.
	
	// main쓰레드 : main메서드의 작업을 수행하는 것도 쓰레드.
	
	// 실행중인 사용자 쓰레드가 하나도 없을때 프로그램 종료됨.
	
	// Thread.sleep(1000);  // 쓰레드를 1초간 멈추게 함.
	
	// 쓰레드의 실행제어
	// sleep() : 지정된시간(천분의 일초 단위)동안 쓰레드를 일시정지 시킴, 지정한 시간이 지나면 자동적으로 다시 실행
	// join() : 지정된 시간동안 쓰레드가 실행, 지정된 시간이나 작업 종료시 join()을 호출한 쓰레드로 다시돌아와 실행.
	// interrupt() : sleep(), join()에 의해 일시정지상태인 쓰레드를 깨워 실행대기 상태로 만듬
	// stop() : 쓰레드 즉시 종료
	// suspend() : 쓰레드 일시정지
	// resume() : suspend()에 의해 일시정지상태를 실행대기상태로 만듬.
	// static void yield() : 실행 중에 자신에게 주어진 실행시간을 다른 쓰레드에게 양보하고 자신은 실행대기 상태가 됨.
	
	// 쓰레드 동기화 : 한 쓰레드가 진행 중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것.
	// synchronized 동기화
	// 1. 메서드 전체를 임계 영억으로 지정 :
	// 		public synchronized void calcSum() { }
	// 2. 특정한 영역을 임계 영역으로 지정 
	// 		synchronized(객체의 참조변수) { }
	
	// wait(), notify(), notifyAll()
	// 1.Object에 정의 되어 있음.
	// 2. 동기화 블록(synchronized블록)내에서만 사용
	// 3. 보다 효율적인 동기화를 가능하게 함.
	// wait() : 실행 중이던 쓰레드는 해당 객체의 대기실에 있음. 즉 쓰레드가 락을 반납하고 기다림.
	// notify() : 해당 객체의 대기실에 있던 모든 쓰레드 중에서 임의의 쓰레드만 받음.
	// notifyAll() : 기다리고 있는 모든 쓰레드에게 통지. 하지만 Lock을 얻을 수 있는 것은 하나의 쓰레드.
	
	// Lock, Condition
	// ReentrantLoock : 재진입이 가능한 lock. 가장 일반적인 배타 lock.
	// ReentrantReadWriteLock : 읽기에는 공유적이고, 쓰기에는 배타적인 lock.
	// StampedLock : ReentrantReadWriteLock에 낙관적인 lock의 기능을 추가
	
	// fork, join 프레임웍
	// RecursiveAction : 반환값이 없는 작업을 구현 할 때 사용.
	// RecursiveTask : 반환값이 있는 작업을 구현 할 때 사용.
	// fork() : 해당 작업을 쓰레드 풀의 작업 큐에 넣음. 비동기 메서드.
	// join() : 해당 작업의 수행이 끝날 때 까지 기다렸다가, 수행이 끝나면 그 결과를 반환.
	
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
