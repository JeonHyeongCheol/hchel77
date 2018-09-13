package if_;

import java.io.IOException;
import java.util.Scanner;

public class ThreeNum { // 세가지 수 비교 후 두 번째 수 출력
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		int num=0;
		
		if((a >= b && b >= c) || (c >= b && b >= a)) {
			num = b;
		} else if((b >= a && a >= c) || (c >= a && a >= b)) {
			num = a;
		} else {
			num = c;
		}
		System.out.println(num);
	}
}


/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException { // Collections.sort 이용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> al = new ArrayList<>();
		al.add(Integer.parseInt(st.nextToken()));
		al.add(Integer.parseInt(st.nextToken()));
		al.add(Integer.parseInt(st.nextToken()));
		Collections.sort(al);
		System.out.println(al.get(1));
	}
}
*/

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args)
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// TODO Auto-generated method stub
		try
		{
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			
			int[] array = new int[st.countTokens()];
			
			for (int i = 0; i < array.length; i++)
			{
				array[i] = Integer.parseInt(st.nextToken());
			}
			
			int instance;
			
			for (int i = 0; i < array.length-1; i++)
			{
				for (int j = 1; j < array.length; j++)
				{
					if (array[i] > array[j])
					{
						instance = array[i];
						array[i] = array[j];
						array[j] = instance;
					}
				}
			}
			System.out.println(array[array.length/2]);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
*/