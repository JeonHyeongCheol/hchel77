package pack5;

public class AniCow extends Animal {
	// 부모클래스의 추상메소드를 모두 해줘야지 Err가 뜨지 않음.
	@Override
	public String name() {
		return "소";
	}
	
	@Override
	public String eat() {
		return "풀";
	}
	
	@Override
	public String action() {
		return "낮";
	}
	

}
