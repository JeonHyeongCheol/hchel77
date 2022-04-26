package if_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class PlusCircle { // 더하기 사이클, 첫번째, 실패
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String first = br.readLine();
		String in = first;
		int cou = 0;
		while(true) {
			if(first.length() == 1) first += "0";
			int[] sum = new int[2];
			int hap = 0;
			for (int i = 0; i < first.length(); i++) {
				sum[i] = first.charAt(i) - '0';
				hap += first.charAt(i) - '0';
			}
			String haps = Integer.toString(hap);
			if(haps.length() == 2) {
				int[] sum2 = new int[haps.length()];
				for (int i = 0; i < haps.length(); i++) {
					sum2[i] = haps.charAt(i) - '0';
				}
				hap = sum2[1];
			}
			String hap2 = Integer.toString(sum[1]) + hap;
			cou++;			
			if(in.equals(hap2)) {
				System.out.println(cou);
				return;
			}
			first = hap2;
		}
	}
	
	/*
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
		 	int n = sc.nextInt();
		    int index = 0, ans = 0, sum;
		    ans = n;
		    while (true){

		    	int x = n / 10;
		    	int y = n % 10;
		    	sum = (x+y) % 10;
		    	n = y * 10 + sum;
		    	
		    	index++;
		    	if (ans == n) {
		    		break;
		    	}
		    }
		 
		    System.out.println(index);
		    sc.close();
	}
	 */
}

