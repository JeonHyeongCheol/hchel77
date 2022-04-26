import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer_Tc implements Runnable{
	ServerSocket ss;
	Service service;
	ArrayList<Service> list = new ArrayList<>();
	
	public ChatServer_Tc() {
		try {
			ss = new ServerSocket(7778);
			System.out.println("채팅 서버 서비스 시작...");
			
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println("ChatServer err:" + e);
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Socket socket = ss.accept(); //client connect wait
				service = new Service(socket);
				service.start();
				service.chat_name = service.in.readLine();
				
				service.messageSend("/c" + service.chat_name);
				for(Service ser:list) {
					ser.messageSend("/c" + service.chat_name);
					service.messageSend("/c" + service.chat_name);
				}
				list.add(service);
			} catch (Exception e) {
				System.out.println("ChatServer run err :" + e);
			}
		}
	}
	
	//client별 처리용 내부 클래스 -----------------
	class Service extends Thread{
		String chat_name;
		BufferedReader in;
		OutputStream out;
		Socket socket;
		
		public Service(Socket socket) {
			try {
				this.socket = socket;
				in = new BufferedReader(
						new InputStreamReader(
							socket.getInputStream(), "euc-kr"));
				out = socket.getOutputStream();
			} catch (Exception e) {
				System.out.println("Service err : " + e);
			}			
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					String msg = in.readLine();
					if(msg == null || msg.equals("")) continue;
					
					//메세지가 있는 경우 처리
					if(msg.charAt(0) == '/') { //약속이 있는 메시지
						if(msg.charAt(1) == 'r') { //대화명 변경
							messageAll("/r" + chat_name + "-" + msg.substring(2));
							chat_name = msg.substring(2);
						}else if(msg.charAt(1) == 'q') { //퇴장
							try {
								messageAll("/q" + chat_name);
								list.remove(this);
								in.close();
								out.close();
								socket.close();
							} catch (Exception e) {
								// TODO: handle exception
							}
							break;
						}else if(msg.charAt(1) == 's') { //귓속말
							//  /s접속자명-메시지
							String name = msg.substring(2, msg.indexOf('-')).trim();
							for(Service ser : list) {
								if(name.equals(ser.chat_name)) {
									ser.messageSend(chat_name + 
											">(secret)" + 
											msg.substring(msg.indexOf('-') + 1));
								}
							}
						}
					}else { //일반 메시지 처리
						messageAll(chat_name +">" + msg);
					}
				} catch (Exception e) {
					break;
				}
			}
		}
		
		public void messageAll(String msg) {
			try {
				for (int i = 0; i < list.size(); i++) {
					Service ser = list.get(i);
					ser.messageSend(msg);
				}
			} catch (Exception e) {
				System.out.println("messageAll :" + e);
			}
		}
		
		public void messageSend(String msg) {
			try {
				out.write((msg + "\n").getBytes("euc-kr"));
			} catch (Exception e) {
				System.out.println("messageSend :" + e);
			}
		}
	}
	//--------------------------------------------
	
	public static void main(String[] args) {
		new ChatServer();
	}
}
