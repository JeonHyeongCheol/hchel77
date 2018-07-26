package pack5;

public class AnimalUtil {
	public static void find(Animal animal) { //animal 타입만 들어 올 수 있음. Ani Cow,Lion.
		animal.print();
		
		if(animal instanceof AniCow) { 
			// instanceof 객체타입비교연산자, ture, false 둘 중에 하나 반환.
			// 어떤 타입에 대한 instanceof 연산의 결과가 true라는 것은 검샇산 타입으로 형변환이 가능하다는 것을 뜻함.
			Animal a = animal; // Animal a = (AniCow)animal; 같은 것.
			System.out.println("이름 : " +  a.name());
		} else if(animal instanceof AniLion) {
			Animal b = animal;
			System.out.println("이름 : " +  b.name());
		} else {
			System.out.println("기타 동물");
		}
	}
}
