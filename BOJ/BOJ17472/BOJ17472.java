package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 17472 다리만들기2
 */
public class BOJ17472 {
	static int[][] map;
	static int[][] graph;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					dfs(i, j, cnt);
					++cnt;
				}
			}
		}
		graph = new int[cnt][cnt];
		for (int i = 0; i < cnt; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 1) {
					// 재귀로 주변 탐색 그래프 찾기
					for (int d = 0; d < 4; d++) {
						makeGraph(i, j, d);
					}
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		for (int i = 0; i < cnt; i++) {
//			System.out.println(Arrays.toString(graph[i]));
//		}
		System.out.println(prim());
	}

	private static int prim() {
		int[] minEdge = new int[cnt];
		boolean[] visited = new boolean[cnt];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

		pq.offer(new Edge(2, 0));
		int node = 0, ans = 0;
		Edge cur;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			if (visited[cur.v])
				continue;
			ans += cur.w;
			visited[cur.v] = true;
			if (++node == cnt - 2)
				return ans;

			for (int i = 0; i < cnt; i++) {
				if (graph[cur.v][i] < 2 || graph[cur.v][i] == Integer.MAX_VALUE)
					continue;
				if (!visited[i] && minEdge[i] > graph[cur.v][i]) {
					minEdge[i] = graph[cur.v][i];
					pq.offer(new Edge(i, minEdge[i]));
				}
			}
		}
		return -1;
	}

	private static void makeGraph(int i, int j, int d) {
		int r = i, c = j, dist = 0;
		while (true) {
			r = dr[d] + r;
			c = dc[d] + c;
			if (isIn(r, c)) {
				if (map[r][c] != 0) {
					if (map[r][c] != map[i][j] && graph[map[i][j]][map[r][c]] > dist) {
						if(dist<2) break;
						graph[map[i][j]][map[r][c]] = dist;
						graph[map[r][c]][map[i][j]] = dist;
					}
					break;
				}
				++dist;
			} else {
				break;
			}
		}
	}

	private static void dfs(int i, int j, int cnt) {
		map[i][j] = cnt;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (isIn(nr, nc) && map[nr][nc] == 1) {
				dfs(nr, nc, cnt);
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

}
