package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/** 개근상
 *  https://www.acmicpc.net/problem/1563
 */
public class BOJ1563 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int ans=0;
		
		int[][][] dp=new int[2][3][N];
		dp[0][0][0]=1;
		dp[1][0][0]=1;
		dp[0][1][0]=1;
		
		for(int i=1;i<N;i++) {
			dp[0][0][i]=dp[0][0][i-1]+dp[0][1][i-1]+dp[0][2][i-1];
			dp[0][1][i]=dp[0][0][i-1];
			dp[0][2][i]=dp[0][1][i-1];
			dp[1][0][i]=dp[0][0][i-1]+dp[0][1][i-1]+dp[0][2][i-1]+dp[1][0][i-1]+dp[1][1][i-1]+dp[1][2][i-1];
			dp[1][1][i]=dp[1][0][i-1];
			dp[1][2][i]=dp[1][1][i-1];
			
			for(int k=0;k<2;k++) {
				for(int j=0;j<3;j++) {
					dp[k][j][i]=dp[k][j][i]%1000000;
				}
			}
		}
		
		for(int i=0;i<2;i++) {
			for(int j=0;j<3;j++) {
				ans+=(dp[i][j][N-1]%1000000);
			}
		}
		System.out.println(ans%1000000);
		
	}
}
