package pack3;

public class StuPage extends Student{
	 String name = "페이지";               //자식이 있을수도 있고 없을수도 있기때문에 지워서 해도 된다.
	 int grade = 2;                      //있으면 자식을 거느릴수있다.
	 String gender = "f";
	
	public StuPage() {
		System.out.println("StuPage 생성자");
	}
	
	@Override // 기존의 상속받은 메서드의 내용을 변경하는 것
	protected void study() {
		System.out.println("학생은 자바+python을 잘 해야 한다");
	}
	
	public void mySpec() {
		System.out.println("보유기술 : 프로그래밍 전문");
	}
	
	public void print() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(name);
		System.out.println(this.name);
		System.out.println(super.name);
		study();
		this.study();
		super.study();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
	
}
