package pack6;

public class R_Tom implements Resume { // 인터페이스는 상속이라고도 하는데 구현이 맞다.
	private String irum, phone, juso; // 인터페이스를 받아도 변수는 써줘야 함. 받을 변수.
	
	public R_Tom() {
	}
	
	@Override
	public void setIrum(String irum) {
		this.irum = irum;
		
	}
	
	@Override
	public void setPhone(String phone) {
		this.phone = phone;
		
	}

	public void setJuso(String juso) {
		this.juso = juso;
	}
	
	@Override
	public void print() {
		// Resume.SIZE = "b5"; // err : final 이므로
		System.out.println("용지 규격은 " + Resume.SIZE);
		System.out.println("이름은 : " + irum + ", 전화 : " + phone + ", 주소 : " + juso);
		
		playJava(true); 
		Resume.changeData();
		
	}
	
}
