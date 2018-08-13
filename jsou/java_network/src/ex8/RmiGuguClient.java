package ex8;
import java.rmi.Naming;
import java.util.Scanner;

public class RmiGuguClient {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("몇 단 : ");
		
		try {
			RmiGuguInter rh = (RmiGuguInter)Naming.lookup("rmi://127.0.0.1:1099/mbc");	
			String result = rh.guguDan(sc.nextInt());
			
			System.out.println(result);
			
		} catch (Exception e) {
			System.out.println("Clietn err : " + e);
		}
	}
}
