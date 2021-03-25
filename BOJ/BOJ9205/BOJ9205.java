package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9205 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		int max = Integer.MAX_VALUE << 1;
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N + 2][N + 2];
			boolean[][] b = new boolean[N + 2][N + 2];
			Pair[] p = new Pair[N + 2];
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				p[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			int dist = 0;
			for (int i = 0; i < N + 2; i++) {
				for (int j = i+1; j < N + 2; j++) {
					dist = getDist(p[i], p[j]);
					if (dist <= 1000) {
						b[i][j] = true;
						b[j][i] = true;
					}
				}
			}
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					for (int j = 0; j < N + 2; j++) {
						if (b[i][k] && b[k][j]) {
							b[i][j] = true;
						}
					}
				}
			}
			if (b[0][N + 1]) {
				bw.write("happy\n");
			} else {
				bw.write("sad\n");
			}
			bw.flush();
		}
		bw.close();
	}

	private static int getDist(Pair p1, Pair p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
