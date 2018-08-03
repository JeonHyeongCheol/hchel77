package pack2;

public class OurProjectMain {

	public static void main(String[] args) {
			System.out.println("뭔가 하다...");
			
			Animal animal = new Animal("사자", 5);
			animal.display();
			
			System.out.println();
			MyCalc calc = new MyCalc();
			System.out.println(calc.goPlus(3.4, 10)); // 더하기			
			System.out.println(calc.goDiv(3.4, 10)); // 나누기	
			System.out.println(calc.goMul(3.4, 10)); // 곱하기	
			System.out.println(calc.goTri(5, 10)); // 삼각형 넓이
			System.out.println(MyCalc.msg);
			System.out.println();
			System.out.println(Math.PI);
			System.out.println(Math.max(445600, 131200));

					
	}
}
