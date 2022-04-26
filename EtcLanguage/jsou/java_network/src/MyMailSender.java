import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyMailSender {
// 메일 처리 기본 지식
//	Session : 메일 서버와의 세션 관리
//	Message : 메일 메시지 정보 및 내용
//	Address : 이메일 주소
//	Authenticator : 아이디/비밀번호 정보
//	Transport : 메일 전송
//	Store / Folder : 메일 박스 및 메일 폴더 지정
	
// 환경설정 Properties
//	Properties props = new Properties();
//	props.put("mail.smtp.starttls.enable", "true");
//	props.put("mail.transport.protocol", "smtp");
//	props.put("mail.smtp.host", "smtp.gmail.com");
//	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//	props.put("mail.smtp.port", "465");
//	props.put("mail.smtp.auth", "true");

	public static void main(String[] args) {
		String password = "acorn1234test";
		String toMail = "acorngoodjob@gmail.com";
		String fromName = "공기밥";
		String subject = "점심시간";
		String content = "배고프다아아아";
		
		MyMailSender mailSender = new MyMailSender();
		mailSender.sendMail(toMail, password, fromName, subject, content);
	}
	
	public void sendMail(String toMail, String password, String fromName, String subject, String content) {
		try {
			// Smtp 환경설정 Properties
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");

			// 인증
			MyAuth auth = new MyAuth(toMail, password);
			Session sess = Session.getDefaultInstance(props, auth); // 정보 주는 것
			
			// 메세지
			MimeMessage msg = new MimeMessage(sess);
			msg.setHeader("content-type", "text/plain;charset=utf-8"); // 문서에 대한 타입을 주는 것.
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail, fromName, "utf-8")); // 여기나 toMail값 변경해서 보내면 됨.

			// 전송
			msg.setSubject(subject);
			msg.setContent(content, "text/plain;charset=utf-8");
			msg.setSentDate(new Date());
			
			// 첨부파일
			// ...
			
			Transport.send(msg);
			System.out.println("메일전송 완료");
			
		} catch (Exception e) {
			System.out.println("sendMail err : " + e);
		}
	}
}
