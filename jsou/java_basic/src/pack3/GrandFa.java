package pack3;

public class GrandFa {
	// 상속 : 기존의 클래스를 재사용하여 새로운 클래스를 작성 하는 것.
	// 상속을 하게되면 적은 양의 코드로 새로운 클래스 작성 및 코드를 공통적으로 관리 가능하여 코드의 추가 및 변경이 매우 용이함.
	
	// 조상 클래스 : 부모(parent)클래스, 상위(super)클래스, 기반(base)클래스
	// 자손 클래스 : 자식(child)클래스, 하위(sub)클래스, 파생된(derived)클래스
	
	// 생성자와 초기화 블럭은 상속되지 않음. 멤버만 상속
	// 자손 클래스의 멤버 개수는 조상 클래스보다 항상 같거나 많음.
	
	// 자손 클래스의 인스턴스를 생성하면 조상 클래스의 멤버와 자손 클래스의 멤버가 합쳐진 하나의 인스턴스로 생성
	
	// 원은 점이다. - Circle is a Point.
	// 원은 점을 가지고 있다. - Circle has a Point.
	
	// 상속관계 '~은 ~이다.(is-a)'
	// 포함관계 '~은 ~을 가지고 있다.(has-a)' -> 클래스 객체를 만들어주면 포함관계과 됨.
	
	// 
	private int nai = 80;
	public String gabo = "상감청자";
	protected String gahun = "자바를 정복하자";    // 이 클레스는 프로텍티드(protected)부모를 가지고 있는 자식에게는 참조가 가능.
	public String data = "GrandFa data";
	                                          // 를 보고 자식을 거느리고 있구나 라고 판단. 생략 = 현패키지에선 가능 다른대로가면 불가.
	                                          // 프로텍티드 같은경우 다른 패키지에서 자식클레스 에게는 참조할수있다.
	public GrandFa() {
		System.out.println("할아버지 생성자");
	}
	
	public GrandFa(String ss) {
		//.....
	}
	
	public GrandFa(String ss, int aa) {
		//.....
	}
	
	public GrandFa(int nai) {
		this();                   // ()가 있으면 생성자, 없으면 지적하는거다.   생성자는 생성자를 부를수있다.
		this.nai = nai;           //  () 안에 글을 적으면 스트링에 가고 비어있을시 빈 생성자에게 간다.
	}
	
//	public GrandFa(int nai) {
//		this.nai = nai;
//	}
	
	public String say() {              // 메소드에 final이 있으면 오버라이드 불가.
		return "할아버지 말씀: 그날 그날 정리해라";    // 맴버변수에 final을 주면 수정 불가.
	}
	
	public final void eat() {
		System.out.println("밥은 맛있게...");
	}
	
	public int getNai() {
		return nai;
	}
}
