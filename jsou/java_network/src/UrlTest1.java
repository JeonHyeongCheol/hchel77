import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlTest1 {

	public UrlTest1(String str) {
		try {
			URL url = new URL(str);
			
			InputStream in = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String ss = "";
			
			while((ss = br.readLine()) != null) {// null이 아닌동안
				System.out.println(ss);
			}
			in.close();
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		new UrlTest1("https://www.daum.net");

	}
}
