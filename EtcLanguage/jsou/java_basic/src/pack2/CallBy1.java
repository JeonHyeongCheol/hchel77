package pack2;

public class CallBy1 {
	// 클래스의 또 다른 정의
	// 1. 변수 : 하나의 데이터를 저장할 수 있는 공간.
	// 2. 배열 : 같은 종류의 여러 데이터를 하나의 집합으로 저장할 수 있는 공간.
	// 3. 구조체 : 서로 관련된 여러 데이터를 종류에 관계없이 하나의 집합으로 저장 할 수 있는 공간.
	// 4. 클래스 : 데이터와 함수의 결합(구조체 + 함수)
	
	int a = 10, b = 20;   //기본형 맴버 변수
	int c[] = {1,2};       //참조형 맴버 변수
	
	// 기본형 매개변수, 참조형 매개변수
	// 기본형 매개변수 : 변수의 값을 읽기만 할 수 있음(read only).
	// 참조형 매개변수 : 변수의 값을 읽고 변경 할 수 있음(read & write).
	
	// 변수의 초기화
	// 지역변수는 사용하기 전에는 반드시 초기화 해야함.
	// 멤버변수와 배열의 초기화는 선택적이지만, 지역변수의 초기화는 필수적임.
	
	// 명시적 초기화 : 변수를 선언과 동시에 초기화 하는 것.
	
	// 초기화 블럭
	// 클래스 초기화 블럭 : 클래스 변수의 복잡한 초기화에 사용
	// 인스턴스 초기화 블럭 : 인스턴스 변수의 복잡한 초기화에 사용
	// 다른 클래스에서 상속 받아 공통으로 사용해야 하는 경우 클래스 초기화 블럭을 사용하면 됨.
	// 클래스의 모든 생성자에 공통으로 수행되어야 하는 문장이 있을 경우 인스턴스 초기화 블럭에 사용하면 됨.
	
	// 클래스명 { }
	// { } <- 인스턴스초기화 블럭은 이름이 따로 없어 됨. 하지만 연달아서 사용 해야 함.
	
	// 멤버변수의 초기화 시기와 순서
	// 클래스변수의 초기화 시점 : 클래스가 처음 로딩될때 단 한번 초기화 함.
	// 인스턴스변수의 초기화 시점 : 인스턴스가 생성될 때 마다 각 인스턴스 별로 초기화가 이루어짐.
	// 클래스변수의 초기화 순서 : 기본값 -> 명시적초기화 -> 클래스 초기화블럭
	// 인스턴스변수의 초기화 순서 : 기본값 -> 명시적초기화 -> 인스턴스 초기화 블럭 -> 생성자
}