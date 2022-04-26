package pack7;

public class Outer {

	private int a;
	private Inner inner; // 내부클래스 객체 변수 설정.
	// 내부클래스 작성 방법 : 내부클래스 만들고 → 멤버 변수에 객체 변수 설정 → 생성자에 객체 선언 → 메소드에서 객체 메소드불러 오듯이 불러옴(ex]inner.cc()).
	public Outer() {
		a = 10;
		inner = new Inner(); // 객체 생성
	}
	
	public void aa() {
		System.out.println("외부에 있는 aa 메소드");
		bb();
		inner.cc(); // 내부클래스에 있는 메소드를 가져오기 위해서는 객체 생성이 되어야지 부를 수 있음.
	}
	
	public void bb() {
		System.out.println("외부에 있는 bb 메소드");
	}
	
	public class Inner { // 내부 클래스. 바깥쪽에 있는 접근 영향을 받음
		int b;
		
		public Inner() {
			b = 20;
		}
		
		void cc() { // Inner클래스에 메소드
			System.out.println("내부에 있는 cc 메소드");
			System.out.println("a : " + a + "b : " + b);
			bb(); // 내부 클래스에서는 바깥쪽 메소드를 불러올수 있음.
		}
		
		
	}
	
	public static void main(String[] args) {
		
		// 내부클래스 안에 있는 메소드를 불러 올 수 있는 방법 3가지
		Outer outer = new Outer();
		outer.aa();
		
		Outer.Inner inn = outer.new Inner();
		inn.cc();
		
		outer.inner.cc();//
	}
	
}
