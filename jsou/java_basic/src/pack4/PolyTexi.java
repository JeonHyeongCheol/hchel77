package pack4;

public class PolyTexi extends PolyCar{
	private int passenger = 2;
	
	public void show() {
		System.out.println("난 택시입니다.");
	}
	
	@Override
	public void dispDate() {
		System.out.println("택시 승객은 :" +  passenger);
	}
}
