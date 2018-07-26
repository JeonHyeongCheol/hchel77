package pack2;

public class Pro {

	public static void main(String[] args) {
		System.out.println("______");
				
		Production goods = new Production(3000, "과자"); // 새로운 객체 생성시 바로 받아 생성자에 입력.
		goods.setNum(34);
//		goods.setNumber(231);
		
		goods.display();

	}

}
