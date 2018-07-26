package pack2;

public class ProgrammerMain {
	public static void main(String[] args) {
		Programmer tom = new Programmer(); //생성자 호출
		//tom.Progammer(); // 생성자 호출 불가
		System.out.println("tom 별명은 " + tom.nickName);
		tom.nickName = "자바 귀신";
		System.out.println("tom 별명은 " + tom.nickName);

		//System.out.println("tom의 나이는 " + tom.age);

		tom.setAge(24);
		System.out.println("tom의 나이는 " + tom.getAge());
		System.out.println("tom 기술" + tom.spec);
		
		tom.displayData();
		tom.spec += ",파이썬 전문가";
		tom.displayData();
		
		System.out.println();
		System.out.println(tom.motto);
		System.out.println(Programmer.motto);
		//tom.PI = 12.3;
		//System.out.println("파이는 " + tom.PI); <-비권장
		System.out.println("파이는 " + Programmer.PI);

		//System.out.println(Math.PI);
		

		
		System.out.println();
		Programmer oscar= new Programmer();
		oscar.displayData();

	}

}
