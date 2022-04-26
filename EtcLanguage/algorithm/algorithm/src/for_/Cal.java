package for_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class Cal { // 요일 출력하기

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a[] = br.readLine().split(" ");
		
		Calendar cal = Calendar.getInstance();
		int month = Integer.parseInt(a[0]);
		int date = Integer.parseInt(a[1]);
		
		System.out.println(month + " " + date);
		
		cal.set(Calendar.YEAR, 2007);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, date);
		
		String WDay [] = {"","SUN","MON", "TUE", "WED", "THU", "FRI", "SAT"}; 
		
		
		System.out.println(WDay[(cal.get(Calendar.DAY_OF_WEEK))]);
		
		
		
	}

}
/*
import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String args[]) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int dday = 0;
		int ddday = 0;
		for(int i = 1; i < a; i++) {
			
			if( i == 2) dday += 28;
			else if( i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) dday += 31;
			else dday += 30;
			
		}
		
		dday += b;
		
		ddday = dday%7;
		
		if(ddday == 0) System.out.println("SUN");
		if(ddday == 1) System.out.println("MON");
		if(ddday == 2) System.out.println("TUE");
		if(ddday == 3) System.out.println("WED");
		if(ddday == 4) System.out.println("THU");
		if(ddday == 5) System.out.println("FRI");
		if(ddday == 6) System.out.println("SAT");
		
	}
	
}
*/
