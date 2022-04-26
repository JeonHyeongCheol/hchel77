package pack2;

public class AnimalMain { 

	public static void main(String[] args) {
		System.out.println("뭔가를 하다가...");
		
		Animal tiger = new Animal(); 
		// Animal 객체를 생성해 그 주소를 tiger에 저장, New 결과는 생선된 객체의 주소를 얘기함
		tiger.display();
		tiger.display(5);
		tiger.display("호돌이");
		tiger.display(7, "호순이");
		tiger.display("호랭이", 7);
		
		System.out.println();
		System.out.println();
		
		Animal hen = new Animal(2);
		hen.display();
		hen.display("통닭", 1);
		
	     System.out.println();
         Animal wolfDog = new Animal("늑대");
	     wolfDog.display();
	     
	     System.out.println();
	     Animal dog = new Animal("멍멍이",3);
	     dog.display();
	     
	     System.out.println("\n--------------static---------------");
	     dog.myStaticMethod();
	     Animal.myStaticMethod();
	     dog.normalMethod();
	}


	
}
