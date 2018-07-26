package pack5;

public class JepumTv extends Jepum{ 

		public JepumTv() {
			System.out.println("TV 생성자");
			// 보이지 않지만 super가 있는 것임(부모클래스).
		}
		
		@Override
		public void volumeControl() { //추상메소드로 override하게 되면 옆에 삼각형 모양이 뜸.
			System.out.println("티브이 소리 조절");
		}
}
