package pack;

import java.io.PrintWriter;

public class ServletPoham {
	private String irum;
	
	public ServletPoham(String irum) {
		this.irum = irum;
	}
	
	public String getIrum() {
		return irum;
	}
	
	public void display(int n, PrintWriter out) { // ServletGo에 있는 PrintWriter out(파라미터)을 써서 매개 변수(args)를 받아 오면 됨.
		int su = 10 / n;
		out.println("<br>연산결과 : " + su);
	}
}
