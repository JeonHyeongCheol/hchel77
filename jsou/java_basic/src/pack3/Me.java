package pack3;

public final class Me extends Father{
	
	private int nai = 20;
	public final String irum = "유일해";   //값 수정 불가
	// final - 마지막의, 변경될 수 없는
	// final 사용될 수 있는 곳 - 클래스, 메서드, 멤버변수, 지역변수
	// 클래스 - 변경될 수 없는 클래스, 확장 될 수 없는 클래스
	// 메서드 - 변경될 수 없는 메서드, final로 지정된 메서드는 오버라이딩을 통해 재정의 될 수 없음.
	// 멤버변수, 지역 변수 - 변수 앞에 final이 붙으면, 값을 변경할 수 없는 상수가 됨.
	
	// final 멤버 변수의 초기화 할 수 있는 방법 : 
	// 상수지만 선언과 함께 초기화하지 않고 생성장에서 단 한번 초기화 할 수 있음.
	
	// 제어자 : 클래스, 변수 또는 메서드의 선언부에 함께 사용되어 부가적인 의미를 부여
	// 접근 제어자 : public, protected default, private
	// 그 외 : static, final, abstract, native, transient, synchronized, volatile, strictfp
	// private : 같은 클래스 내에서만 접근 가능
	// default : 같은 패키지 내에서만 접근 가능
	// protected : 같은 패키지 내에서, 그리고 다른 패키지의 자손 클래스에 접근 가능
	// public : 접근 제한이 전혀 없음.
	// 접근범위가 넓은 쪽에서 좁은 쪽 : public > protected > (default) > private
	// 접근 제어자를 사용하는 이유 : 외부로부터 데이터를 보호하기 위해, 외부에는 불필요한, 내부적으로만 사용되는, 부분을 감추기 위해서
	
	// 제어자의 조합
	// 클래스 : public, (default), final, abstract
	// 메서드 : 모든 접근 제어자, final, abstract static
	// 멤버변수 : 모든 접근 제어자, final, static
	// 지역변수 : final
	// 주의 할 점
	// 1. 메서드에 static과 abstract를 함께 사용 할 수 없음. - static메서드는 몸통이 있는 메서드만 사용 할 수 있기 때문.
	// 2. 클래스에 abstract와 final을 동시에 사용 할 수 없음. - final은 확장, abstract는 상속 이기 때문에 두 개를 사용하면 모순.
	// 3. abstract메서드의 접근제어자가 private일 수 없음. - abstract는 자손 클래스를 구현해줘야 하는데 private이면 접근 할 수 없음.
	// 4. 메서드에 privat과 final을 같이 사용할 필요는 없음. - private인 메서드는 오버라이딩 될 수 없음.
	
	// Static - 클래스의, 공통적인
	// static이 붙은 멤버변수, 메서드, 초기화 블럭은 인스턴스가 아닌 클래스에 관계되어있기 때문에 인스턴스를 생성하지 않고 사용 가능
	// 인스턴스 메서드와 static메서드의 근본적인 차이는 메서드 내에서 인스턴스 멤버를 사용하는가의 여부
	// static이 사용될 수 있는 곳 - 멤버변수, 메서드, 초기화 블럭
	// 멤버 변수 : 모든 인스턴스에 공통적으로 사용되는 클래스 변수, 인스턴스를 생성하지 않고 사용 가능, 클래스가 메모리 로드될 때 생성
	// 메서드 : 인스턴스를 생성하지 않고도 호출이 가능한 static메서드가 됨, static메서드 내에서는 인스턴스 멤버들을 직접 사용 할 수 없음.
	public String data = "Me data";                    //지역 데이터가 우선순위다.
	
	public Me() {
		super(20);
		System.out.println("내 생성자");
	}
	
	public /*final*/ void play() {                       //me에만 가지고있는 고유의 메소드   여기서의 final은 의미가 없다 더 불러올 사람이 없기때문.
		System.out.println("하고 싶은 거 하기");
	}
	
	@Override
	public int getNai() {
		return nai;
	}
	
//	public final void eat() {
//		System.out.println("밥은 맛있게...");
//	}

	public void displayDate() {
		String data = "displayDate method의 data";                   //우선순위의 데이터를 찾아간다  (클레스 맴버의 데이터)
		System.out.println(data);
		System.out.println(this.data);
		System.out.println(super.data);                         //부모의 데이터를 볼려면 super을 써야함.
		
	}
}