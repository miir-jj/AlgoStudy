package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * 색종이 붙이기
 */
public class BOJ17136 {
	static int[][] map;
	static int[] colored;
	static int ans=Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		colored = new int[6];
		Arrays.fill(colored, 5);
		StringTokenizer st;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		countColored(0, 0, 0);
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);
	}

	private static void countColored(int r, int c, int cnt) {
		if(r>=9&&c>9) {
			ans=ans<cnt?ans:cnt;
			return;
		}
		if(ans<=cnt)
			return;
		if(c>9) {
			countColored(r+1, 0, cnt);
			return;
		}
		if (map[r][c] == 1) {
			for (int k = 5; k > 0; k--) {
				if (colored[k]>0&&check(r, c, k)) {
					coloring(r,c,k,0);
					--colored[k];
					countColored(r,c+k,cnt+1);
					coloring(r,c,k,1);
					++colored[k];
				}
			}
		}else {
			countColored(r, c+1, cnt);
		}
	}

	private static void coloring(int r, int c, int k, int n) {
		for(int i=r,end1=r+k;i<end1;i++) {
			for(int j=c,end2=c+k;j<end2;j++) {
				map[i][j]=n;
			}
		}
	}

	private static boolean check(int r, int c, int k) {
		for(int i=r,end1=r+k;i<end1;i++) {
			for(int j=c,end2=c+k;j<end2;j++) {
				if(i>9||j>9||map[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}

}
