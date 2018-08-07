import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest2 {
	public static void main(String[] args) {
		try {
			// 소켓 대 소켓 통신
			InetAddress ia = InetAddress.getByName("www.daum.net"); // 주소를 얻음.
			Socket socket = new Socket(ia, 80); // Client(클라이언트) Socket.
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))); // 서베어서 파일을 가져오는데 인코딩과, buffer를 사용
			// PrintWrite() : 데이터를 기반 스트림에 다양한 형태로 출력할 수 있는 print, println, printf와 같은 메서드를 오버로딩 하여 제공.
			// BufferedReader() : 버퍼를 이용해 입출력의 효율을 높일 수 있도록 해주는 역할. readLine() 사용시 데이터를 라인단위로 읽고 
			// BufferedWriter() : newLine()이라는 줄바꿈 해주는 메서드를 가짐.
			// OutPutStreamWriter() : 바이트기반 스트림을 문자기반 스트림으로 연결 시켜주는 역할. 지정된 인코딩의 문자데이터로 변환 해줌.
			out.println("GET http://www.daum.net"); // 요청하는 것.
			out.flush();
			
			// 웹서버에서 넘어온 자료 출력.
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				String str = reader.readLine();
				if(str == null) break;
				System.out.println(str);
			}
			reader.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("err : " + e);
		}
	}
}
