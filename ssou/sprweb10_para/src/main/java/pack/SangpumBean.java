package pack;

import org.springframework.stereotype.Component;

@Component
public class SangpumBean { // 폼빈, 컴멘드 객체
	private String Sang;
	private int su, dan;
	
	public String getSang() {
		return Sang;
	}
	
	public void setSang(String sang) {
		Sang = sang;
	}
	
	public int getSu() {
		return su;
	}
	
	public void setSu(int su) {
		this.su = su;
	}
	
	public int getDan() {
		return dan;
	}
	
	public void setDan(int dan) {
		this.dan = dan;
	}
	
	
}
