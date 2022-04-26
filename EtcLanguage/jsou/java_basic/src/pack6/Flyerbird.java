package pack6;

public class Flyerbird implements Flyer {

	@Override
	public void fly() {
		System.out.println("날개를 저으며 앞으로 날아감");	
	}
	
	@Override
	public boolean isAnimal() {
		return true;
	}
}
