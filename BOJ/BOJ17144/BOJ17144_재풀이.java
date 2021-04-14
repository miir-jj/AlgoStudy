package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17144_재풀이 {
	static int R, C, T, cleaner, map[][], temp[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		temp = new int[R][C];
		int[] up = { 3, 0, 2, 1 };
		int[] down = { 3, 1, 2, 0 };

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					cleaner = i;
					map[i][j] = 0;
				}
			}
		}

		for (int t = 0; t < T; t++) {
			spread();
			clean(up, cleaner - 1);
			clean(down, cleaner);
		}

		int ans = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				ans += map[i][j];
			}
		}
		System.out.println(ans);
	}

	private static void spread() {
		for (int i = 0; i < R; i++) {
			Arrays.fill(temp[i], 0);
		}
		int nr, nc, cnt;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] >= 5) {
					cnt = 0;
					for (int d = 0; d < 4; d++) {
						nr = r + dr[d];
						nc = c + dc[d];
						if (isIn(nr, nc) && !((nr==cleaner | nr==cleaner-1)&&nc==0)) {
							temp[nr][nc] += map[r][c] / 5;
							++cnt;
						}
					}
					map[r][c] -= (map[r][c] / 5) * cnt;
				}
				temp[r][c] += map[r][c];
			}
		}
		temp[cleaner - 1][0] = 0;
		temp[cleaner][0] = 0;
		for (int i = 0; i < R; i++) {
			System.arraycopy(temp[i], 0, map[i], 0, C);
		}
	}

	private static void clean(int[] dir, int cl) {
		int nr = cl, nc = 0;
		for (int d = 0; d < 4; d++) {
			while (true) {
				nr += dr[dir[d]];
				nc += dc[dir[d]];
				if (nr == cl && nc == 0)
					return;
				if(!isIn(nr,nc)) break;
				map[nr][nc] = temp[nr - dr[dir[d]]][nc - dc[dir[d]]];
			}
			nr -= dr[dir[d]];
			nc -= dc[dir[d]];
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
