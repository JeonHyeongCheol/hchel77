package db.pack1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

// properties는 키 = 값 으로 써야 함.

// properties 값을 보기 위해 이클립스에 설치하고 싶을 때 
// eclipse properties editor 검색하여 설치
public class PropertiesRead {

	public static void main(String[] args) {
		Properties properties = new Properties();
		
		// read
		try {
			properties.load(new FileInputStream("\\work\\jsou\\java_inter\\src\\db\\pack1\\ex1.properties"));
			System.out.println(properties.getProperty("mes1"));
			System.out.println(properties.getProperty("mes2"));
		} catch (Exception e) {
			System.out.println("읽기 실패 : " + e);
		}
		
		// write
		try {
			properties.setProperty("mes1", "nice");
			properties.setProperty("mes2", "good");
			properties.setProperty("mes3", "hello");
			properties.store(new FileOutputStream("\\work\\jsou\\java_inter\\src\\db\\pack1\\ex1.properties"), null);
			System.out.println("저장 성공");
		} catch (Exception e) {
			System.out.println("쓰기 실패 : " + e);
		}

	}

}
