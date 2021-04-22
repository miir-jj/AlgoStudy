package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 등산로 조성 */
public class SWEA1949 {
	static int N, K, ans, map[][];
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		ArrayList<int[]> list;
		int max;
		for (int t = 1, end = Integer.parseInt(br.readLine().trim()); t <= end; t++) {
			bw.write("#" + t + " ");
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			list = new ArrayList<int[]>();
			max = 0;
			ans = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > max) {
						max = map[i][j];
						list.clear();
						list.add(new int[] { i, j });
					} else if (map[i][j] == max) {
						list.add(new int[] { i, j });
					}
				}
			}
			for (int[] i : list) {
				visited[i[0]][i[1]] = true;
				dfs(i, 1, false);
				for (int k = 0; k < N; k++) {
					Arrays.fill(visited[k], false);
				}
			}
			bw.write(ans + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static void dfs(int[] i, int len, boolean cut) {
		int nr, nc;
		for (int d = 0; d < 4; d++) {
			nr = i[0] + dr[d];
			nc = i[1] + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
				int diff = map[nr][nc] - map[i[0]][i[1]];
				if (diff < K) {
					visited[nr][nc] = true;
					if (diff < 0) {
						dfs(new int[] { nr, nc }, len + 1, cut);
					} else if (!cut) {
						map[nr][nc] -= (diff + 1);
						dfs(new int[] { nr, nc }, len + 1, true);
						map[nr][nc] += (diff + 1);
					}
					visited[nr][nc] = false;
				} else {
					ans = ans > len ? ans : len;
				}
			}
		}
	}

}
