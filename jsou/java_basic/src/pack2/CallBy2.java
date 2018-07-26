package pack2;

public class CallBy2 {
	public void ex(int a, int b) { // 인자로 기본형    콜바이벨류 void = 빈손
		int imsi = a;              //기억장소 맞바꾸는법
		a = b;
		b = imsi;
//		if(a == 5) {               이렇게도 쓸수있다.
//			b = imsi;
//			return;               매소드의 탈출
//		}
		System.out.println("메소드 내의 a:" + a + ", b : " + b);
	}

	public void ex(CallBy1 data) { // 인자로 참조형 변수   콜바이레퍼런스
		int imsi = data.a;
		data.a = data.b;
		data.b = imsi;
		System.out.println("메소드 내의 a:" + data.a + ", b : " + data.b);
	}
	
	// 참조형 반환타입
	// 반환타입이 '참조형'이라는 것은 메서드가 '객체의 주소'를 반환한다는 것을 의미.

	public void ex(int[] ar) { // 인자로 참조형 변수(배열)
		int imsi = ar[0];      // c[0] = c[1]번째 바꾼거다.
		ar[0] = ar[1];
		ar[1] = imsi;

	}
}
