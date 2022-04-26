package pack6;

public class FlyerMain {

	public static void main(String[] args) {
		System.out.println(Flyer.FAST);
		
		Flyerbird bird = new Flyerbird();
		bird.fly();
		FlyerAirplane airplane = new FlyerAirplane();
		airplane.fly();
		
		System.out.println();
		FlyerUtil.showData(bird);
		System.out.println();
		FlyerUtil.showData(airplane);

	}

}
