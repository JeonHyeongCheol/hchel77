package pack4;

public class PolyCar {
	protected int speed = 80;                  //자식은 다른패키지에서 가능하지만 다른것들은 같은 패키지에서만 사용가능.
	
	public PolyCar() {
		System.out.println("난 자동차야~~~~");
	}
	
	public void dispDate() {
		System.out.println("속도: " + speed);
	}
	
	public int getSpeed() {
		return speed;
	}
}
