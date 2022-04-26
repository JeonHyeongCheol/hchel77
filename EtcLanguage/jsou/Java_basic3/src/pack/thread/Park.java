package pack.thread;

public class Park extends Thread {
	@Override
	public void run() {
//		Bank ba = new Bank();
//		ba.saveMoney(5000);
		BankMain.myBank.saveMoney(5000); // Main에서 객체 선언 후 모두 사용 할 수 있어서 사용 가능. 5000원을 입금.
		System.out.println("남편 예금 후 잔고 : " + BankMain.myBank.getMoney());
	}
}
