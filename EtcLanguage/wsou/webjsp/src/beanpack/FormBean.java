package beanpack;

public class FormBean {
	private String name;
	private int kor; // jsp action tag 사용하면 숫자도 변환 해줌
	private int eng;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getKor() {
		return kor;
	}
	
	public void setKor(int kor) {
		this.kor = kor;
	}
	
	public int getEng() {
		return eng;
	}
	
	public void setEng(int eng) {
		this.eng = eng;
	}
}
