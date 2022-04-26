package if_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Avg { // ЦђБе
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int a = Integer.parseInt(br.readLine());
		String[] in = br.readLine().split(" ");
	
		String temp;
		
		for (int i = in.length; 1 < i; i--) {
			for (int j = 0; j < i-1; j++) {
				if(Integer.parseInt(in[j]) > (Integer.parseInt(in[j+1]))) {
					temp = in[j];
					in[j] = in[j+1];
					in[j+1] = temp;
				}
			}
		}
		
		double max = Integer.parseInt((in[in.length - 1]));
		double sum = 0;
		for (int i = 0; i < in.length; i++) {
			sum += 100 * (Integer.parseInt(in[i]) / max);
		}
		double avg = sum/a;
		System.out.println(avg);
	}
}

/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		if(n>=0 && n<=1000){
			
			st = new StringTokenizer(br.readLine());
			
			double max = 0;
			double[] array = new double[n];
			
			for(int i=0; i<n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
				
				if(array[i] > max) {
					max = array[i];
				}
			}
			double sum = 0;
			
			for(double num : array) {
				sum= sum+((num/max)*100);
			}
			
			bw.write(String.format("%.2f", (sum/n)));
			
			bw.flush();
			bw.close();
		}
	}

}

*/