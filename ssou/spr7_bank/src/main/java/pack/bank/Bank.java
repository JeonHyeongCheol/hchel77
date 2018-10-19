package pack.bank;

public interface Bank {
	void inputMoney(int money); // 입금
	void outputMoney(int money); // 출금
	int getMoney();
}
