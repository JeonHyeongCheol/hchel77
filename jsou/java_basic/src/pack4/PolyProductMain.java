package pack4;

public class PolyProductMain {

	public static void main(String[] args) {              //추상클래스 오버라이딩을 강요한다.
		PolyProduct product = null;                //객체변수만 만들다. 다형성을 위해서 새로운 객체를 만들 필요는 없고, 객체 변수만 만들어주면 됨.
		
		PolyRadio radio = new PolyRadio();
		radio.setValumn(10);
		System.out.println(radio.getValumn());
		radio.volumnControl();
		
		System.out.println();
		PolyTv tv = new PolyTv();
		tv.setValumn(7);
		System.out.println(tv.getValumn());
		tv.volumnControl();
		tv.tvShow();
		
		System.out.println("\n 다형성");
		product = radio;
		product.volumnControl();
		product = tv;
		product.volumnControl();
//		product.Show();
		
		System.out.println();
		PolyProduct poly[] = new PolyProduct[2];
		poly[0] = radio;
		poly[1] = tv;
		for(PolyProduct i:poly) {
			i.volumnControl();
		}
	}

}
