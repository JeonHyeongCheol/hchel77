package pack4;

public class DogMain {

	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.print();
		System.out.println(dog.callName());
		
		System.out.println("HouseDog------------");
		HouseDog hd = new HouseDog("집개");
		hd.print();
		hd.show();
		System.out.println(hd.callName());
		
		System.out.println("WolfDog------------");
		WolfDog wd = new WolfDog("늑대");
		wd.print();
		wd.show();
		System.out.println(wd.callName());
		wd.display();
		
		System.out.println("---------------------------------------------------");
		WolfDog bushdog = wd;   // 같은타입이라 변수에서 변수로 줄수있다.
		bushdog.print();
		
		System.out.println();   // 자식 클레스에 가지고 있는 객체를 부모 객체변수에 취할수있다.
		Dog dog2 = wd;          // 부모의 객체변수의 이름으로 자식의 메소드를 부를수있다.
		dog2.print();           // 자식이 부모에게 객체를 주는것은 프로모션 부모가 자식에게 주는건 "캐스팅"해줘야 가능.
		//dog2.show();          // 자식이 오버라이딩 된 메소드는 사용 가능 하지만 오버라이딩 되지 않는 것은 사용할 수 없음.
		//dog2.display();       // 오버라이딩 된 매소드만 가능    자식 고유의 메소드는 호출 불가
		
		//bushdog = dog;  (전혀불가) // 부모 자신의 객체를 자식에게 취하하는건 불가하다.
		//wd = dog2;  (불가)
		wd = (WolfDog)dog2;               // 자식이 가지고있는 객체는 부모에게 주는건 가능  울프독을 선언해주고는 가능
		bushdog = (WolfDog)dog2;          // 부모가 가지고 있는 객체는 자식에게 주는건 불가
//		bushdog = "(WolfDog)<--캐스팅  "dog2;
		
		Dog coyote = new Dog("코요테");
		coyote.print();
		System.out.println(coyote.callName());
		coyote = bushdog;                  // 자식 객체를 부모에게 주는건 당연가능
		coyote.print();                    
		System.out.println(dog2);          // 주소는 같지만 다른 개체로 인식
		System.out.println(hd);   // 추상클레스  인터페이스
		
	}
}
