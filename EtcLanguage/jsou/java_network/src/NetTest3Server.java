import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class NetTest3Server {
	public static void main(String[] args) {
		ServerSocket ss = null;
		
		// 내 컴퓨터가 사용중인 port number 확인
		/*
		for (int i = 0; i < 65536; i++) {
			try {
				ss = new ServerSocket(i);
				ss.close();
			} catch (Exception e) {
				System.out.println(i + "번 포트 사용 중");
			}
		}
		*/
		
		Socket socket = null; // 원격 시스템과 통신 가능.
		try { // 서버 한 번만 돌게 함.
			ss = new ServerSocket(9999); // 9999번 포트를 열음
			System.out.println("서버 서비스 중...");
			socket = ss.accept(); // 다른 곳에서 이쪽 서버에 접속시 객체를 만듬.
			
			System.out.println("접속자 정보 : " + socket.toString());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 수진자료 받기.
			String data = reader.readLine(); 
			System.out.println("수신자료 : " + data);
			
			reader.close();
			socket.close();
			ss.close();
		} catch (Exception e) {
			System.out.println("Server Err");
		}
	}
}
