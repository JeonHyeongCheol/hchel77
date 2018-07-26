package pack7;

import pack1.Test1;

public class A {
	// 내부 클래스 : 클래스 내에 선언된 클래스, 선언 이유 : 두 클래스가 서로 긴밀한 관계에 있기 때문
	// AWT, Swing과 같은 GUI어플리케이션의 이벤트처리외에는 잘 사용하지 않음.
	
	// 내부 클래스 장점
	// 내부 클래스에서 외부 클래스의 멤버들을 쉽게 접근 가능
	// 코드의 복잡성을 줄일 수 있음(캡슐화)
	
	// 내부 클래스의 종류와 특징
	// 인스턴스 클래스 : 외부 클래스의 멤버변수 선언위치에 선언, 외부 클래스의 인턴스턴스 멤버처럼 다룸, 
	//				주로 외부 클래스의 인스턴스 멤버들과 관련된 작업에 사용 될 목적으로 사용.
	// 스태택 클래스 : 외부 클래스의 멤버변수 선언위치에 선언, 외부 클래스의 static 멤버처럼 다룸,
	//			    주로 외부 클래스의 static 멤버, 특히 static메서드에서 사용될 목적으로 사용
	// 지역 클래스 : 외부 클래스의 메서드나 초기화 블럭 안에 선언하며, 선언된 영역 내부에서만 사용
	// 익명 클래스 : 클래스의 선언과 객체의 생성 동시에 하는 이름없는 클래스(일회용)
	
	// 내부 클래스의 제어자와 접근성 : 인스턴스 클래스, 스태틱 클래스는 외부 클래스의 멤버변수와 같은 위치에 선언
	//						  멤버변수와 같은 성질을 가지며, 인스턴스 멤버와 static멤버 간의 규칙이 내부 클래스에도 똑같이 적용.
	//						  내부 클래스도 클래스이기 때문이 abstract, final과 같은 제어자, private, protected 접근제어자도 사용 가능.
	//						  내부 클래스 중에서 스태틱 클래스만 static 멤버를 가질 수 있음.(내부 클래스에 static변수를 선언해야하면 static클래스로 선언해야 함)
	//						  인스턴스 클래스는 외부 클래스의 인스턴스 멤버를 객체 생성 없이 바로 사용 가능 하지만,
	//						  스태틱 클래스는 외부 클래스의 인스턴스 멤버를 객체생성 없이 사용 할 수 없음.
	
	int field1; // 바깥쪽 클래스 멤버
	
	public A() {
		System.out.println("A 객체 생성");
	}
	
	void method1() { // 바깥쪽 클래스 멤버 method
		System.out.println("method1");
	}
	

	
	
	
	class B { // 인스턴스 멤버 클래스
		int field2;
		
		public B() {
			System.out.println("B object 생성");
		}
		
		void method2() {
			System.out.println("method2 수행" + field2);
		}
	}
	
	static class C {
		int field3;
		
		public C() {
			System.out.println("C 오브젝트 생성");
		}
		
		void method3() {
			System.out.println("method2 수행" + field3);
		}
	}
	
	void method4() { // 바깥쪽 클래스 멤버 method
		System.out.println("method4 처리 됨");
		
		class D { // 메소드 안에 있는 클래스
			int field4;
			
			public D() {
				System.out.println("D 인스턴스(생성자)");
			}
			
			void method5() {
				System.out.println("method5 처리" + field4);
			}
		}
		D d = new D(); // 메소드 안에 있는 클래스 사용시. 메소드 안에서 객체를 선언해주어야 함. 바깥쪽에서 접근 할 수 없기 때문에 불가능.
		d.field4 = 7;
		d.method5();
	}
	
	// 허용 범위
	B b2 = new B(); // 클래스내에서 사용시 가능. main 메소드에서는 A.B로 써야지 접근하여 사용.
	C c2 = new C();
	//D d2 = new D(); // 메소드 안에 있기 때문에 찾을 수 없어 사용 할 수 없음.
	
	void test1() {
		B b3 = new B();
		C c3 = new C();
		
	}
	
	static void test3() { // 일반메소드는 스태틱을 부를 수 있지만 static 메소드는 일반메소드를 불러 올 수 없음.
		C c4 = new C(); // 스태틱메소드이기 때문에 접근 가능.
		//B b4 = new B(); // 일반메소드이기 때문에 접근 불가능.
		
	}
	
	public static void main(String[] args) {
		System.out.println("바깥쪽 클래스 객체 생성-------");
		A a = new A();
		a.field1 = 1;
		a.method1();
		
		System.out.println("\n 인스턴스 멤버 클래스 객체 생성------");
		//B b = new B(); // B는 A의 클래스의 멤버이기 때문에 마음대로 접근하지 못함.
		A.B b = a.new B(); // 내부클래스 사용시 이렇게 사용하여야 함.
		b.field2 = 2;
		b.method2();
		
		System.out.println("\n정적 멤버 클래스 객체 생성-------");
		A.C c = new A.C();
		c.field3 = 3;
		c.method3();
		C c2 = new C(); // 정적이기 때문에 그냥 사용해도 가능. B는 접근하지 못하지만, C는 정적이여서 마음대로 접근 가능.
		c2.field3 = 4;
		c2.method3();
		
		System.out.println("\n로컬 클래스 객체 생성-------");
		a.method4();
		
		System.out.println();
		a.test1();
	}
}
