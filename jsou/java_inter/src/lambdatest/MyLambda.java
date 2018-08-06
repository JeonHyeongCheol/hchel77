package lambdatest;

public class MyLambda {
	
	static class Inner implements MyInter {
		@Override
		public int addData(int a, int b) {
			return a + b;
		}
	}
	
	public static void main(String[] args) {
		Inner inner = new Inner(); // 전통적 방식
		System.out.println(inner.addData(3, 4));
		
		System.out.println();
		MyInter myInter = (a, b) -> a + b; // 람다식 // a,b 값을 받아서 화살표로 넘겨서 리턴값을 받아서 출력
		System.out.println(myInter.addData(3, 4));
		
		System.out.println();
		MyInter myInter2 = (a, b) -> a * b; 
		System.out.println(myInter2.addData(3, 4));
	}

}
