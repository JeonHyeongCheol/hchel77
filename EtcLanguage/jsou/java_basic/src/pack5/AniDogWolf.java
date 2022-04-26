package pack5;

public class AniDogWolf extends AniDog{ 
	// Animal에 있는 추상메소드 세 개중에 AniDog에서 name을 물려 받았기 때문에 AniDogWolf에서는 eat, action만 받으면 됨.
	@Override
	public String eat() {
		return "늑대 밥";
	}
	
	@Override
	public String action() {
		return "밤낮";
	}
}
