package pack.thread;

public class Bank {
	private int money = 10000; // 공유자원
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	// 스레드의 동기화 : 스레드 간 자원공유
	public synchronized void saveMoney(int save) { // 입금 // synchronized : 스레드 간 자원공유
		int m = this.getMoney();
		try {
			Thread.sleep(2000); // 일부로 지연시간 2초를 줌.
		} catch(Exception e) {
				
		}
		this.setMoney(m + save);
	}
	
	public synchronized void minusMoney(int mon) { // 출금
		int m = this.getMoney();
		try {
			Thread.sleep(3000); // 일부로 지연시간 3초를 줌.
		} catch(Exception e) {
				
		}
		this.setMoney(m - mon);
	}
}
