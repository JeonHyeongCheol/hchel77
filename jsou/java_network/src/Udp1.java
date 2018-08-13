import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Udp1 { // 전송 담당
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("보낼 자료 입력 : ");
		String msg = in.readLine();
		
		// 한명 한테만 보내기
		/*
		DatagramSocket dsoc = new DatagramSocket(); // 우체통
		InetAddress ia = InetAddress.getByName("192.168.0.18");
		DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ia, 7777);
		dsoc.send(dp);
		dsoc.close();
		System.out.println("전송 완료");
		*/

		DatagramSocket dsoc = new DatagramSocket(); // 우체통

		for(int i = 1 ; i < 100; i ++) {
			InetAddress ia = InetAddress.getByName("192.168.0." + i);
			DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ia, 7777);
			dsoc.send(dp);
			System.out.println("전송 완료 : 192.168.0." + i);
		}
		dsoc.close();
	}
}
