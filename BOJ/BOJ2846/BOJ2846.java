package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 2846오르막길 
 * 	간단한 증가수열 문제
 * */
public class BOJ2846 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int first = num[0], last = 0, max = 0;
		for(int i = 1; i < N; i++) {
			if(last < num[i]) {
				last = num[i];
			} else {
				first = num[i];
				last = num[i];
			}
			max = max > last - first ? max : last - first;
		}
		System.out.println(max);
	}
}
