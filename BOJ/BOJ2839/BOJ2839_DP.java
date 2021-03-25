package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2839_DP {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int[] w=new int[N+1];
		Arrays.fill(w, Integer.MAX_VALUE);
		w[0]=0;
		
		for(int i=0;i<=N;i++) {
			if(w[i]==Integer.MAX_VALUE) continue;
			if(i+3<=N) w[i+3]=w[i]+1<w[i+3]?w[i]+1:w[i+3];
			if(i+5<=N) w[i+5]=w[i]+1<w[i+5]?w[i]+1:w[i+5];
		}
		if(w[N]==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(w[N]);
	}
}
