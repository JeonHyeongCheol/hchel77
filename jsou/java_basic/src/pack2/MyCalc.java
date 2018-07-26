package pack2;

public class MyCalc {
		// 나만의 계산기 클래스
	private double no1, no2;
	public static String msg = "자바는 웹용 언어야~";
	
	public double goPlus (double no1, double no2) { // 더하기
		return no1 + no2 + 1000;
	}
	
	public double goMinus (double no1, double no2) { // 빼기 
		double imsi = no1;
		return no1 - no2 + 1000;	
	}
	
	public double goMul (double no1, double no2) { // 곱하기
		return no1 * no2 + 1000;	
	
}
	
	public double goDiv (double no1, double no2) { // 나누기
		return no1 / no2 + 1000;	
		}


	public String goTri(double no1, double no2) {
		double result = no1 * no2 / 2;
		return "삼각형의 넓이는 " + result;
	}		

	
}
	

