package pack2;

public class CallByMain {

	public static void main(String[] args) {
		// 인자 전달시 call by value, call by feference
		CallBy1 my = new CallBy1();
		CallBy2 your = new CallBy2();
		
		System.out.println("원래 a:" + my.a + ", b: " + my.b);       // 1에 있는 a,b와 2에 있는 a,b는 다른 개체다
		                                                           // 즉 개체가 4개 생성
		your.ex(my.a,my.b);   //인수로 기본형 변수를 사용(값이 전달)          변수가 가지고 있는 값만 넘어간것이다.
		
		System.out.println("1.수행후 a:" + my.a + ", b: " + my.b);
		
		System.out.println();
		your.ex(my);          //인수로 참조형 변수를 사용(주소 전달)
		System.out.println("2.수행후 a:" + my.a + ", b: " + my.b);
		
		System.out.println();
		your.ex(my.c);          //인수로 참조형 변수를 사용(주소 전달) - 배열
		System.out.println("3.수행후 c[0]:" + my.c[0] + ", c[1]: " + my.c[1]);
                         //has a(포함관계) ,    is a (상속관계)
	}

}
//          oop : 자원의 재 활용
//          클레스 에서 클레스 불러오는게 조립식이다.
