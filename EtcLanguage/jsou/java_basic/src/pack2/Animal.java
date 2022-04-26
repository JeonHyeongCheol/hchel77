package pack2;

public class Animal {
	// 객체지향언어
	// 1. 코드의 재사용성이 높음 : 새로운 코드를 작성할 때 기존의 코드를 이용하여 쉽게 작성 할 수 있음.
	// 2. 코드의 관리가 용이 : 코드간의 관계를 이용해서 적은 노력으로 쉽게 코드를 변경할 수 있음.
	// 3. 신뢰성이 높은 프로그래밍을 가능 : 제어자와 메서드를 이용해서 데이터를 보호, 올바른 값을 유지, 코드의 중복 제거하여 코드의
	// 불일치로 인한 오동작을 방지 함.

	// 클래스와 객체의 정의와 용도
	// 클래스의 정의 : 클래스란 객체를 정의 해놓은 것.
	// 클래스의 용도 : 클래스는 객체를 생성하는데 사용.
	// 객체의 정의 : 실제로 존재하는 것. 사물 또는 개념.
	// 객체의 용도 : 객체가 가지고 있는 기능과 속성에 따라 다름.
	// 유형의 객체 : 책상, 의자, 자동차, TV와 같은 사물.
	// 무형의 객체 : 수학 공식, 프로그램 에러와 같은 논리나 개념.

	// 인스턴스 : 어떤 클래스로부터 만들어진 객체를 그 클래스의 인스턴스라 함.
	// 인스턴스화 : 클래스로부터 객체를 만드는 과정.

	// 객체의 구성요소 - 속성과 기능
	// 속성(property) : 멤버변수, 특성, 필드, 상태
	// 기능(function) : 메서드, 함수, 행위

	// 인스턴스는 참조 변수를 통해서만 다룰 수 있으며, 참조변수의 타입은 인스턴스의 타입과 일치해야 함.
	
	// 클래스 메소드와 인스턴스 메소드
	// 1. 클래스를 설계시, 멤버 변수 중 모든 인스턴스에 공통으로 사용하는 것에 static을 붙임.
	// 2. 클래스변수(static)는 인스턴스를 생성하지 않아도 사용 가능(static이 붙은 변수는 클래스가 메모리에 올라 갈 때 자동으로 생성).
	// 3. 클래스 메소드는 인스턴스 변수를 사용 할 수 없음.
	// 4. 메소드 내에서 인스턴스 변수를 사용하지 않는다면, static을 붙이는 것을 고려 할 것.
	// 정리 : 클래스의 멤버변수 중 모든 인스턴스에 공통된 값을 유지해야 하는 것이 있는지 살펴보고 static을 붙여 줌.
	//		 작성한 메소드 중에서 인스턴스 변수나 인스턴스 메소드를 사용하지 않는 메소드에 static을 붙일 것을 고려.
	
	private int leg = 4;
	private int age = 0; // "private int age;" 과 같음. 즉, 초기값을 주지 않으면 값이 0과 같음
	private String name;
	private static int mouse = 1;

	public Animal() {
		// 생성자는 내용이 없을 시 생략가능 (컴파일러가 생성)

	}

	public Animal(int leg) { // constructor overload(ing) 생성자 오버로딩 // 정수형 변수
		this.leg = leg; // 생성자지만 오버로딩을 위해 여러가지 변수로 하여 사용
	}

	public Animal(String irum, int nai) { // constructor overload(ing) 생성자 오버로딩
		// 문자열 변수, 정수형 변수, 순서에 맞게 사용하여야 함.
		this.name = irum; // 생성자지만 오버로딩을 위해 여러가지 변수로 하여 사용
		age = nai;

	}

	public Animal(String name) { // constructor overload(ing) 생성자 오버로딩 // 문자열 변수
		this.name = name; // 생성자지만 오버로딩을 위해 여러가지 변수로 하여 사용
		// 생성자는 내용이 없을 시 생략가능 (컴파일러가 생성)

	}

	public void display() {
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);
	}

	public void display(int age) { // method overloading(ing) - 동일한 이름의 메소드가 존재할려면 argument 의 변수가 달라야 한다. // 정수형 변수
		this.age = age; // 전역변수와 지역변수를 구분하기위해 "this"를 씀
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);
	}

	public void display(String name) { // method overloading(ing) - 동일한 이름의 메소드가 존재할려면 argument 의 변수가 달라야 한다. // 문자열 변수
		this.name = name; // 전역변수와 지역변수를 구분하기위해 "this"를 씀
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);
	}

	public void display(int age, String str) { // method overloading(ing) - 동일한 이름의 메소드가 존재할려면 argument 의 갯수, 변수 및 타입이
												// 다르면 성립한다. // 정수형 변수, 문자열 변수
		// Main 메소드에서 사용시 순서에 맞게 사용하여야 함.

		this.age = age; // 전역변수와 지역변수를 구분하기위해 "this"를 씀
		name = str;
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);
	}

	public void display(String str, int age) { // method overloading(ing) - 동일한 이름의 메소드가 존재할려면 argument 의 갯수, 변수, 타입 순서가
												// 다르면 성립한다.
		// Main 메소드에서 사용시 순서에 맞게 사용하여야 함.
		this.age = age; // 전역변수와 지역변수를 구분하기위해 "this"를 씀
		name = str;
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);
	}
	
	// 오버로딩
	// 한 클래스 내에 같은 이름의 메소드를 여러 개 정의 하는 것
	// 오버로딩 조건 : 메소드 이름이 같아야 하고 매개변수의 개수 또는 타입이 달라야 함.
	// 오버로딩 장점 : 메소드이름 하나는 어떠한 기능을 할 수 있고 여러가지 기능을 할 수 있으며, 메소드의 이름을 절약 할 수 있음.

	// -----------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx----------//

	/*
	 * public void display(String str, int age) { // method overloading(ing) - 동일한
	 * 이름의 메소드가 존재할려면 argument 의 갯수, 변수, 타입 순서가 다르면 성립한다.
	 * 
	 * this.age = age; // 전역변수와 지역변수를 구분하기위해 "this"를 씀 name = str;
	 * System.out.println("leg:" + leg + ", age:" + age + ", name:" + name); }
	 * public void display(int leg) { // method overloading(ing) - 동일한 이름의 메소드가
	 * 존재할려면 argument 의 갯수, 변수, 타입 순서가 다르면 성립한다.
	 * 
	 * this.age = age; // 전역변수와 지역변수를 구분하기위해 "this"를 씀 name = str;
	 * System.out.println("leg:" + leg + ", age:" + age + ", name:" + name); }
	 * 
	 * public void display( int bb) { this.age = age; name = str;
	 * System.out.println("leg:" + leg + ", age:" + age + ", name:" + name); return
	 * 5;
	 */
	public static void myStaticMethod() {
		System.out.println("난 스태틱 메소드");
		// System.out.println("다리 수:" + leg); <- static member 는 static member만 부를수 있음

		System.out.println("마우스:" + mouse);

	}

	public void normalMethod() {
		System.out.println("난 일반 메소드 ");
		System.out.println("다리 수:" + leg);
		System.out.println("마우스:" + mouse);
	}

}

//
