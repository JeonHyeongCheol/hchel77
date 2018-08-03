package pack3;

public class StudentMain {
	public static void main(String[] args) {
		StuTom tom = new StuTom(); // 자식이 아무것도 없으면 부모에 있는것을 그대로 가져다 씀.
		System.out.println(tom.name);
		System.out.println(tom.grade);
		System.out.println(tom.gender);
		tom.study();
		
		System.out.println("---------------------------------");
		StuPage page = new StuPage(); // 자식이 설정한 것이 있으면 그걸 가져다 씀.
		System.out.println(page.name);
		System.out.println(page.grade);
		System.out.println(page.gender);
		page.study();
		page.mySpec();
		page.print();
	}
}
