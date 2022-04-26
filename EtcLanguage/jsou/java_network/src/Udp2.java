import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Udp2 { // 수신 담당
	public static void main(String[] args) {
		try {
			DatagramSocket dsoc = new DatagramSocket(7777);
			byte[] data = new byte[65500]; // 한번에 받을 수 있는 용량, 단위는 byte
			
			DatagramPacket dp = new DatagramPacket(data, data.length);
			System.out.println("수신대기...........................................");
			
			while(true) {
				dsoc.receive(dp); // receive : 받는다는 것.
				System.out.println("송신 컴 주소 : " + dp.getAddress().getHostAddress());
				System.out.println("자료 크기 : " + dp.getLength());
				System.out.println("자료 내용 : " + new String(dp.getData()).trim());
			}
			
			
		} catch (Exception e) {
			System.out.println("err : " + e);
		}
	}
}
