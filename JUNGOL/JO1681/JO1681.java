package jo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1681 {
	static int N, min;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		min = Integer.MAX_VALUE;
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[1] = true;
		dfs(1, 0, 0);
		System.out.println(min);
	}

	private static void dfs(int pre, int dist, int cnt) {
		if (dist > min)
			return;
		if (cnt == N - 1) {
			if (map[pre][1] != 0) {
				min = min < dist + map[pre][1] ? min : dist + map[pre][1];
			}
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && map[pre][i] != 0) {
				visited[i] = true;
				dfs(i, dist + map[pre][i], cnt + 1);
				visited[i] = false;
			}
		}
	}

}
