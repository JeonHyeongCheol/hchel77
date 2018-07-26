package pack2;

public class Bank {
		private int money = 1000; // 비밀형 정수
		int imsi = 1;
		public int imsi2 = 2;
		
		public Bank( ) { //생성자 오버로딩을 할 경우 내용이 없더라도 반드시 생성자를 써야한다. 
			
		}
		
		public Bank(int money) {
			this.money = money; // 전역변수와 지역변수를 구분하기위해 "this"를 씀
		}
		
		// 생성자에서 다른 생성자 호출하기 - this(), this
		// 생성자의 이름으로 클래스이름 대신 this를 사용.
		// 한 생성자에서 다른 생성자를 호출 할 때는 반드시 첫 줄에서만 호출이 가능.
		// this : 인스턴스 자신을 가리키는 참조변수, 인스턴스의 주소가 저장, 모든 인스턴스메소드에 지역변수로 숨겨진 채로 존재
		// this(), this(매개변수) 생성자, 같은 클래스의 다른 생성자르 호출 할 때 사용.
		
		
		public void dePosit(int amount) { // 입금
			if (amount > 0) money += amount; // amount : 액수			
		}
		
		public void withDraw(int amount) { //출금 
			
			if ((amount > 0) && (money - amount >= 0 )) { // 출금금액과 money에서 amount를 뺀 금액이 0보다 커야함. 	
				  money -= amount; // money에서 amount를 뺌.
			} else {
				System.out.println("출금액이 너무 많아요");
			}
			
		}
		public int getMoney() {  //잔금 확인
			return money;
		}
		
}
