package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *	RGB 거리
 */
public class BOJ1149 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] house=new int[N+1][3];
		int[] color = new int[N];
		for (int i = 1; i <= N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < 3; j++) {
				color[j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.fill(house[i], Integer.MAX_VALUE);
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
					if(k!=j&&house[i][j]>house[i-1][k]+color[j]) {
						house[i][j]=house[i-1][k]+color[j];
					}
				}
			}
		}
		int min=house[N][0]<house[N][1]?house[N][0]:house[N][1];
		min=min<house[N][2]?min:house[N][2];
		System.out.println(min);
	}
}
