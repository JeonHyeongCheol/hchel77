package pack.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pack.model.SangPumInter;

public class SangpumProcess implements Sangpum{
	private SangPumInter sangpumInter; 
	private String sang;
	private int su, dan;
	
	public SangpumProcess(SangPumInter sangpumInter) {
		this.sangpumInter = sangpumInter;
	}
	
	@Override
	public void inputSangpum(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("상품명을 입력하세요 : ");
			sang = br.readLine();
			System.out.println("수량을 입력하세요 : ");
			su = Integer.parseInt(br.readLine());
			System.out.println("단가를 입력하세요 : ");
			dan = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println("inputSangpum Err : " + e);
		}
	}
	
	@Override
	public void outputSangpum() {
		int sum = sangpumInter.calcSqngPum(su, dan);
		StringBuffer sb = new StringBuffer();
		sb.append(sang + " 상품의 금액은 " + sum + "입니다");
		System.out.println(sb.toString());
	}
}
