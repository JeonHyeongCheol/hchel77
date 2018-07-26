package pack3;

public class Student {
	protected String name = "학생";                  //protected = 자식을 거느릴수 있겟다 라고 선언
	protected int grade = 1;
	protected String gender = "m";
	
	public Student() {
		System.out.println("Student 생성자");
	}
	
	protected void study() {
		System.out.println("학생은 자바를 해야 한다");
	}
}
