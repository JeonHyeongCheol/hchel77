package if_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MinX { // X보다 작은 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] in = br.readLine().split(" ");
		
		String[] put = br.readLine().split(" ");
		
		for (int i = 0; i < put.length; i++) {
			if(Integer.parseInt(in[1]) > Integer.parseInt(put[i])) {
				bw.write(put[i] + " ");
			}
		}
		bw.flush();
	}
}
