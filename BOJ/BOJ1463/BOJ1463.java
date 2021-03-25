package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 1463 1로만들기
 */
public class BOJ1463 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int[] cnt=new int[N+1];
		cnt[1]=0;
		for(int i=2;i<=N;i++) {
			cnt[i]=cnt[i-1]+1;
			if(i%3==0) cnt[i]=cnt[i/3]+1<cnt[i]?cnt[i/3]+1:cnt[i];
			if(i%2==0) cnt[i]=cnt[i/2]+1<cnt[i]?cnt[i/2]+1:cnt[i];
		}
		System.out.println(cnt[1]);
	}
}
