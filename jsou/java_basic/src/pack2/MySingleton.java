package pack2;

public class MySingleton {
	int Kor = 90;
	
 private static MySingleton singleton = new MySingleton(); // 새로운 객체 선언
 public static MySingleton getInstance() { 
	 return singleton;
	 
 	}
 
}
