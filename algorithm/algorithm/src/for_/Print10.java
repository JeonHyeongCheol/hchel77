package for_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Print10 { // 10개씩 끊어 출력하기

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] a = (" "+br.readLine()).toCharArray();
		
		for (int i = 1; i < a.length; i++) {
			System.out.print(a[i]);
			if(i%10==0) {
				System.out.println();
			}
		}
	}

}

/*
public class Main {
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder(br.readLine());
	
	    for(int i=0; i< sb.length(); i+=10){
	        if(i+10 < sb.length())
	            System.out.println(sb.substring(i, i+10));
	        else
	            System.out.println(sb.substring(i, sb.length()));
	    }
	}
}
*/