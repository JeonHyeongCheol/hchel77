package shopping;

public class Goods { // DTO : 레코드 단위로 값을 받아옴.
	private String pname;
	private int price;
	
	public Goods(String pname, int price) {
		this.pname = pname; // 생성자를 통해서 값을 가져옴.
		this.price = price;
	}
	
	public String getPname() {
		return pname; // 생성자를 통해 가져온 값을 줄 때 사용.
	}
	
	public int getPrice() {
		return price;
	}
}
