package pack.gui;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MediaTest {
	
	public void kbs() {
		File file; // 파일 불러오기
		AudioInputStream stream; // 오디오 장치와 데이터를 공유할 때 쓰는 스트림이다.
		AudioFormat format; // 재생 포맷
		DataLine.Info info; // 채널정보
		
		file = new File("c:/work/pack/gun.wav");
		
		Clip clip;
		
		try {
			stream = AudioSystem.getAudioInputStream(file);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format); // 오디오 포맷이 args가 됨.
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 파일 단위로 쓸 때는 String을 씀.
	
	
	public static void main(String[] args) {
		MediaTest test = new MediaTest();
		while(true) {
			try {
				test.kbs();
				Thread.sleep(3000); 
				// 3초 마다. 스레드를 상속 받았는데 사용 가능한건 클래스를 바로 불러 메소드를 사용 했기 때문에 다른 언어에서 타이머 사용 하는 것과 동일.
			} catch (Exception e) {
				
			}
		}
	}
}
