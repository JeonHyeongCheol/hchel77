package pack2;

public class Programmer {
		public String nickName; //초기값은 null
		//private int age = 0;
		private int age; // 초기값은 0
		String spec = "자바 개발자";
		
		String name = ""; // 인스턴스 변수
		public static String motto = "미치자"; // 클래스변수
		// static이 붙을 경우 클래스 변수로 변경됨.
		// static 메서드는 같은 클래스 내의 인스턴스 메서드를 호출 할 수 없음.
		// 인스턴스 변수 : 인스턴스가 생성될 때 마다 생성되므로 인스턴스마다 각기 다른 값을 유지.
		// 클래스 변수 : 모든 인스턴스가 하나의 저장공간을 공유하므로 항상 공통된 값을 가짐.
		
		//public final double  PI = 3.14;
		public static final double  PI = 3.14;

		// 생성자
		// 생성자의 이름은 클래스의 이름과 같아야 함.
		// 생성자는 리턴 값이 없음.
		public Programmer() {
			System.out.println("난 생성자. 초기화 담당");
			System.out.println("초기화 없으면 소스코딩 생략");
			age = 20;
		}
		
		public void displayData ( ) {
				String sp = reSpeck();
				System.out.println("별명이 " + nickName + "은(는)" + age + "살" + sp);
		}
		
		private String reSpeck( ) {
			return "보유 기술은" + spec; 
		}
		
		public void setAge(int age) {
			this.age = age;
		}
		
		public int getAge() {
			return age;
			
		
			
		}
}
