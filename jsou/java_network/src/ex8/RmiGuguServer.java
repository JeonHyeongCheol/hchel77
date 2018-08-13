package ex8;
import java.rmi.Naming;

public class RmiGuguServer {
	public static void main(String[] args) {
		try {
			//원격 객체를 작성 후 RMI registry 에 등록
			RmiGuguImpl impl = new RmiGuguImpl();
			Naming.rebind("rmi://127.0.0.1:1099/mbc", impl);
			System.out.println("rmi service start...");
		} catch (Exception e) {
			System.out.println("err : " + e);
		}
	}
}
