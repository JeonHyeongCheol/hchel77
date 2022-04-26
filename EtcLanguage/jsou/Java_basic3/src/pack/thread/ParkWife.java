package pack.thread;

public class ParkWife extends Thread {
	@Override
	public void run() {
//		Bank ba = new Bank();
//		ba.minusMoney(3000);
		BankMain.myBank.minusMoney(2000); // 아내가 2천원 출금.
		System.out.println("아내 출금 후 잔고 : " + BankMain.myBank.getMoney());
	}
}
