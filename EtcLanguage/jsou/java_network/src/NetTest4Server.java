import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetTest4Server {
	ServerSocket ss;
	Socket socket;
	PrintWriter out;
	BufferedReader reader;
	
	public NetTest4Server() {
		try {
			// 통신할 수 있는 객체 만들어 줌. 여기서 멈춰 있다가 클라이언트 들어올 시 객체 생성
			ss = new ServerSocket(8887);
		} catch (Exception e) {
			System.out.println("Test4Server err :" + e);
			return;
		}
		System.out.println("서버 서비스 중....................................");
		
		try { // 서버를 계속 돌리게 하기.
			socket = ss.accept();
			out = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "euc-kr"));
			
		} catch (Exception e) {
			System.out.println("err : " + e);
		}
	}
	
	public void receiveMsg() {
		try {
			String msg = reader.readLine(); // 클라이언트가 넘겨준 메세지를 받음(수신).
			System.out.println("receive Msg : " + msg); // 넘겨준 메세지 출력.
			
			out.println("from server : " + msg + "\n"); // 클라이언트에게 자료 전송(송신).
			
			reader.close();
			out.close();
			socket.close();
			ss.close();
		} catch (Exception e) {
			System.out.println("reciveMsg err : " + e);
		}
	}
	
	public static void main(String[] args) {
		while(true) { // 서버 무한 루프
			NetTest4Server server = new NetTest4Server();
			server.receiveMsg();
		}
	}
}
