import java.rmi.Naming;
import java.rmi.RemoteException;

public class RmiHelloClient {

	public static void main(String[] args) {
		try {
			RmiHelloInter rh = (RmiHelloInter)Naming.lookup("rmi://127.0.0.1:1099/kbs");
			String result = rh.sayHello("");
			System.out.println("result : " + result);
		} catch (Exception e) {
			System.out.println("Client err : " + e);
		}

	}
}
