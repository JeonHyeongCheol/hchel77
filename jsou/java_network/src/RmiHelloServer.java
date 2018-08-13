

import java.rmi.Naming;

public class RmiHelloServer {
	public static void main(String[] args) {
		try {
			// 원격 객체를 작성 후 RMI registry에 등록
			RmiHelloImpl impl = new RmiHelloImpl();
			Naming.rebind("rmi://127.0.0.1:1099/kbs", impl);
			System.out.println("rmi service start...............................");
		} catch (Exception e) {
			System.out.println("err : " + e);
		}
	}
}

// cmd
// rmic rmihelloimpl 실행
// start rmiregistry 실행 시켜 서버를 킴
