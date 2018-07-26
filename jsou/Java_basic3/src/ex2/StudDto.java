package ex2;

public class StudDto {
	private String irum;
	private int kor, eng;
	int avg = 0;
	
	public String getIrum() {
		return irum;
	}
	public void setIrum(String irum) {
		this.irum = irum;
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
	
	public int avg () {
		avg = kor + eng;
		return avg;
	}
	
	
}
