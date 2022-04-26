package etc;

//import pack2.Bank;                                 //패키지가 다를경우 임포트 한다.
//import pack2.Animal;
import pack2.*; //pack2 패키지에 있는 모든 클래스를 사용할수있다.
import java.lang.System; // 월레대로 라면 적어야한다.
import java.lang.String; //배운대로 한다면 써줘야 한다.
import java.util.Scanner;     //그외에 다른 것들은 임포트를 써야한다.  import는 다른 패키지에서 꺼내와서 쓸때 꼭 적어야한다.

public class BankMain2 {

	public static void main(String[] args) {
		// pack2.Bank tom2 = new pack2.Bank(); //패키지가 다를경우 임포트를 안쓸경우 번거롭지만 앞에 붙인다.
		Bank tom2 = new Bank();
		Animal ani = new Animal();

		System.out.println("public member는 project내에서 참조 가능");
		System.out.println(tom2.imsi2); // o 참조가 가능 public 접근 지정자라서.  프로젝트가 달라지면 사용불가
//		Scanner.sc = new Scanner(System.in);
//		System.out.println(tom2.imsi); // x default 접근지정자 

	}

}
