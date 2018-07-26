package pack.thread;

public class BankMain {
	public static Bank myBank = new Bank(); // 멤버변수로 다 사용할 수 있게 함.
	
	public static void main(String[] args) {
		System.out.println("잔고 : " + myBank.getMoney());
		
		Park park = new Park();
		ParkWife wife = new ParkWife();
		
		park.start();
		wife.start();
		
	}
}
