import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetTest4Client {
	
	Socket socket;
	PrintWriter out;
	BufferedReader reader;
	
	public NetTest4Client() {
		try {
			socket = new Socket("192.168.0.57", 8888);
			// out과 reader는 서버와 동일.
			out = new PrintWriter(socket.getOutputStream(), true); // true는 flush
			// flush는 쉽게 말해서 stream에 남아 있는 데이터를 강제로 내보내는 역할.
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "euc-kr"));
		} catch (Exception e) {
			System.out.println("Test4Client err :" + e);
			return;
		}
	}
	
	public void sendMsg() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("전송 메세지 : ");
			String data = scanner.nextLine();
			out.println(data); // 서버로 자료 전송(송신).
			
			String re_data = reader.readLine(); // 서버로 부터 자료 받음(수신).
			System.out.println("수신자료 : " + re_data);
		} catch (Exception e) {
			System.out.println("sendMsg err : " + e);
		} finally {
			try {
				reader.close();
				out.close();
				socket.close();
			} catch (Exception e2) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		NetTest4Client client = new NetTest4Client();
		client.sendMsg();
	}
}
