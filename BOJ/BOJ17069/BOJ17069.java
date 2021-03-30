package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17069 {
	static int N, answer;
	static int[][] map;

	static class Pair {
		int r;
		int c;
		int dir;

		public Pair(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	private static boolean isAvailable(int r, int c) {
		if (r >= N || c >= N || map[r][c] == 1) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		long[][][] dp = new long[3][N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0][1] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (isAvailable(i, j + 1)) {
					dp[0][i][j + 1] = dp[0][i][j] + dp[2][i][j];
				}
				if (isAvailable(i + 1, j)) {
					dp[1][i + 1][j] = dp[1][i][j] + dp[2][i][j];
				}
				if (isAvailable(i + 1, j) && isAvailable(i, j + 1) && isAvailable(i + 1, j + 1)) {
					dp[2][i + 1][j + 1] = dp[0][i][j] + dp[1][i][j] + dp[2][i][j];
				}
			}
		}

		System.out.println(dp[0][N-1][N-1]+dp[1][N-1][N-1]+dp[2][N-1][N-1]);
	}
}
