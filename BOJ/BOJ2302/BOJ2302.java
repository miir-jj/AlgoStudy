package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 극장좌석
 */
public class BOJ2302 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<Integer> num = new ArrayList<>(M);
		for (int i = 0; i < M; i++) {
			num.add(Integer.parseInt(br.readLine()));
		}

		long[] dp = new long[41];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= 40; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		int n=0;
		long ans=1;
		for(int i=1;i<=N;i++) {
			if(num.contains(i)) {
				if(n==0) continue;
				ans*=dp[n];
				n=0;
			}else ++n;
		}
		if(n!=0) ans*=dp[n];
		System.out.println(ans);
	}
}
