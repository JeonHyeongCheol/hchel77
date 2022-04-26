package pack;

public class MyProcess { //setter injection
	private int nai;
	private String name;
	private Showdata showdata;
	
	// Setter만 사용
	
	public void setNai(int nai) {
		this.nai = nai;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setShowdata(Showdata showdata) {
		this.showdata = showdata;
	}
	
	 @Override
		public String toString() {
			// 출력 담당
			return "이름은 " + name + 
					", 나이는 " + nai + 
					", 별명은  " + showdata + // showdata는 원래 객체주소인데 오버라이딩 하기 때문에 값을 받아오는 것. show.tostring을 써도 되고, 안써도 됨. 
					", 취미는 " + showdata.toHobby();
		}
}
