package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2839 {

	public static void main(String[] args) throws Exception {
		int three = 0;
		int five = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int n = N;
		five = n / 5;
		n %= 5;
		three = n / 3;
		n %= 3;

		if (n != 0) {
			while (five > 0) {
				five--;
				n+=5;
				three+=n/3;
				n%=3;
				if (n==0)
					break;
			}
			
		}
		if (n!=0)
			System.out.println(-1);
		else
			System.out.println(five + three);


	}
}
