package boj;

import java.util.Scanner;

/**
 * 18222 투에-모스 문자열
 * @author 미령
 *
 */
public class BOJ18222 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		long k = sc.nextLong() - 1;
		int cnt = 0;
		long flag = 1;
		
		for(int i = 0; i < 61; i++) {
			if((k & flag) != 0) ++cnt;
			flag <<= 1;
		}
		
		System.out.println(cnt % 2);
	}
}
