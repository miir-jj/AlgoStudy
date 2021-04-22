package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * SWEA2105 디저트카페
 * @author 미령
 *
 */
public class SWEA2105 {
	static int N, ans, map[][], start[];
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		boolean[] visited = new boolean[101];
		start = new int[2];
		for (int t = 1, end = Integer.parseInt(br.readLine().trim()); t <= end; t++) {
			bw.write("#" + t + " ");
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			ans = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					Arrays.fill(visited, false);
					visited[map[i][j]] = true;
					start[0] = i;
					start[1] = j;
					dfs(i, j, 0, visited);
				}
			}
			bw.write(ans + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static void dfs(int r, int c, int d, boolean[] visited) {
		if (d >= 4)
			return;
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr == start[0] && nc == start[1]) {
				int cnt = count(visited);
				ans = ans > cnt ? ans : cnt;
				return;
			}
			if (!isIn(nr, nc) || visited[map[nr][nc]])
				return;
			visited[map[nr][nc]] = true;
			dfs(nr, nc, d + 1, Arrays.copyOf(visited, 101));
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static int count(boolean[] visited) {
		int cnt = 0;
		for (int i = 1; i <= 100; i++) {
			if (visited[i])
				++cnt;
		}
		return cnt;
	}
}
