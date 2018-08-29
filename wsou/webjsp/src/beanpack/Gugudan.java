package beanpack;

public class Gugudan {
	
	// 싱글톤 처리
	private static Gugudan gugudan = new Gugudan();
	public static Gugudan getInstance() {
		return gugudan;
	}
	// 싱글톤 처리 여기까지
	public Gugudan() {
		
	}
	
	public int[] compute(int dan) {
		int[] gu = new int[9];
		for (int i = 1; i < 10; i++) {
			gu[i - 1] = dan * i;
		}
		return gu;
	}
}
