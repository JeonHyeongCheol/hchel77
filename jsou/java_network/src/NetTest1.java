import java.net.InetAddress;

public class NetTest1 {
	public static void main(String[] args) {
		InetAddress ia;
		InetAddress ia2[];
		
		try {
			ia = InetAddress.getByName("www.naver.com");
			System.out.println(ia);
			System.out.println(ia.getHostAddress()); // Ip
			System.out.println(ia.getHostName()); // Domain Name
			// 서버 ip를 호스트
			
			System.out.println("-----------------------------------");
			
			ia = InetAddress.getLocalHost();
			System.out.println(ia);
			System.out.println(ia.getHostAddress()); // Ip
			System.out.println(ia.getHostName()); // Domain Name
			
			System.out.println("-----------------------------------");
			
			ia2 = InetAddress.getAllByName("www.daum.net");
			System.out.println(ia2.length);
			for (InetAddress kbs:ia2) {
				System.out.println(kbs.getHostAddress()); // Ip
				System.out.println(kbs.getHostName()); // Domain Name
			}
		} catch (Exception e) {
			System.out.println("net Err : " + e);
		}
	}
}
