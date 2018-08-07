
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest3Client {

	public static void main(String[] args) {
		try {
			//InetAddress ia = InetAddress.getByName("192.168.0.8");
			//Socket socket = new Socket(ia, 9999);
			Socket socket = new Socket("192.168.0.8", 9999);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Hi i'm tom" + "\n"); // 서버로 자료 전송
			out.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Client err : " + e);
		}

	}

}
