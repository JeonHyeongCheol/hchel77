package lambdatest;

public class OurMain {
	public static void main(String[] args) { // 무명클래스로 Override 되는 것은 인터페이스 안에 메소드가 하나 밖에 없기 때문에 객체를 만들시 자동으로 무명클래스화 시킴.
											 // 람다식은 메소드가 하나인 것밖에 안되기 때문, 여러 개의 메소드가 있을 경우 사용하지 못함.
		// 1. 인자가 없는 추상 메소드
		OurInter inter = new OurInter() {
			
			@Override
			public void abc() {
				System.out.println("일반적 익명 클래스의 메소드 오버라이딩");
				
			}
		}; // 내부 무명 클래스
		inter.abc();
		System.out.println();
		
		// 람다식으로 표현
		//OurInter inter2 = () -> {} // 원래 중괄호 있어야 하는데 표현식이 복수개 이면 중괄호 사용. 하나만 있을 때는 생략 가능.  
		OurInter inter2 = () -> System.out.println("일반적 익명 클래스의 메소드 오버라이딩"); // 위에 식을 이렇게 간단히 사용 할 수 있음.
		inter2.abc();
		System.out.println();
		
		OurInter inter3 = () -> {
			System.out.println("람다식으로 표현");
			System.out.println("복수명령문은 {}로 감싸 표현");
		};
		inter3.abc();
		System.out.println("-----------------------------------------------------------------------");
		
		
		// 2. 인자가 있고 void 추상 메소드
		OurInterArg interArg = new OurInterArg() {
			
			@Override
			public void def(int a, int b) {
				System.out.println("두 수의 합은 " + (a + b));
			}
		};
		interArg.def(5, 6);
		System.out.println();
		
		// 람다식으로 표현
		//OurInterArg interArg2 = (a) -> {};
		//OurInterArg interArg2 = a -> {}; // 인자도 하나, 표현식도 하나면 괄호 둘 다 생략 가능. 복수개이면 소괄호, 중괄호 다 사용 하여야 함.
		OurInterArg interArg2 = (a, b) -> {
			System.out.println("람다 사용 : " + (a + b));
		};
		interArg2.def(5, 6);
		System.out.println("-----------------------------------------------------------------------");
		
		// 3. 인자가 있고 int 추상 메소드
		OurInterArgOther other = new OurInterArgOther() {
			
			@Override
			public int def(int a, int b) {
				return a + b;
			}
		};
		int re = other.def(2, 3);
		System.out.println("re : " + re);
		System.out.println();
		
		// 람다식으로 표현
		OurInterArgOther other2 = (a, b)-> {
			return a + b;
		};
		// (a, b) -> a + b; // 요것도 위에랑 동일
		
		int re2 = other2.def(2, 3);
		System.out.println("re 2: " + re2);
	}
}
