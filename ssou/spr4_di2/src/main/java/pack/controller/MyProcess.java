package pack.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import pack.model.MoneyInter;

public class MyProcess implements MyInter{
	private MoneyInter inter; // 참조형
	private int re[]; // 참조형
	
	public MyProcess(MoneyInter inter) { // args는 Context.xml에서 참조되어 생성되는 객체 주소값을 가져옴.
		this.inter = inter;
	}
	
	@Override
	public void inputMoney() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("금액 입력");
			String data = br.readLine();
			int mymoney = Integer.parseInt(data);
			
			re = inter.calcMoney(mymoney); // 계산한 값 반환에서 re에 넣어줌.
		} catch (Exception e) {
			System.out.println("inputMoney Err " + e);
		}
	}
	
	@Override
	public void showMoeny() {
		StringBuffer sb = new StringBuffer();
		sb.append("만원 : " + re[0] + "\n");
		sb.append("천원 : " + re[1] + "\n");
		sb.append("백원 : " + re[2] + "\n");
		sb.append("십원 : " + re[3] + "\n");
		sb.append("일원 : " + re[4] + "\n");
		System.out.println(sb.toString());
	}
}
