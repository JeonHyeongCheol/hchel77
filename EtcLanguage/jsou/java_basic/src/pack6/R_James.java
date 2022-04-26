package pack6;

public class R_James implements Resume {
	
	private String irum, phone, skill;

	public R_James() {
		// TODO Auto-generated constructor stub
	}
	
	@Override // 오버라이드 anotation
	public void setIrum(String irum) {
		if(irum.equals(null)) { // 로컬변수(지역변수)
			this.irum = "무명"; // 전역변수
		} else {
			this.irum = irum;
		}
	}
	
	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	@Override
	public void print() {
		System.out.println("용지 규격은 " + Resume.SIZE);
		System.out.println("이름은 : " + irum + ", 전화 : " + phone + ", 보유기술 : " + skill);
		
		playJava(true);
		
	}
	
	@Override
	public void playJava(boolean b) {
		Resume.super.playJava(b);
	}
	
}
