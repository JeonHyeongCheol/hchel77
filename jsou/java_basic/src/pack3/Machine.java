package pack3;

import java.util.Scanner;

public class Machine {
	private int cupCount, coffee=20, sugar=10;
	private CoinIn co = new CoinIn();
	
	public Machine() {
		// TODO Auto-generated constructor stub
	}
	
	public void showData() {
		Scanner s = new Scanner(System.in);

		System.out.print("동전을 입력하세요 :");
		co.setCoin(s.nextInt());

		System.out.print("몇 잔을 원하세요 :");
		cupCount = s.nextInt();
		System.out.println(co.calc(cupCount));
		System.out.println("성분은 커피:" + coffee + 
				", 설탕:" + sugar + ", 나머진 아리수!");
	}
}
