package arithmetics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Minus { // »©±â

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in [] = br.readLine().split(" ");
		System.out.println(Integer.parseInt(in[0])-Integer.parseInt(in[1]));
	}

}
