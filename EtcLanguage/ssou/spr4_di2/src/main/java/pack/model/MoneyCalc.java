package pack.model;

public class MoneyCalc implements MoneyInter{
	@Override
	public int[] calcMoney(int money) {
		int re[] = new int[5];
		re[0] = money / 10000; // 만원
		re[1] = money % 10000 / 1000; // 천원
		re[2] = money % 10000 % 1000 / 100; // 백원
		re[3] = money % 10000 % 1000 % 100 / 10; // 십원
		re[4] = money % 10000 % 1000 % 100 % 10; // 일원
		return re; // 배열값 리턴.
	}
}
