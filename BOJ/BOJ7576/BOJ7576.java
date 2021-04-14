package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 미령
 * 7576 토마토
 * ****핵심
 * 처음에 익은 토마토는 같은 레벨의 큐에 들어가야 함
 * 한 레벨에서 같이 들어간 토마토를 한번에 큐에서 소진시켜줘야 하는 것
 */
public class BOJ7576 {
	static int M, N, tomato, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					++tomato;
				if (map[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}
		System.out.println(bfs(q));

	}

	private static int bfs(Queue<int[]> queue) {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = queue;
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		int size = 0, cnt = 0, days = 0;
		while (!q.isEmpty()) {
			size = q.size();
			while (size-- > 0) {
				int[] cur = q.poll();
				if (map[cur[0]][cur[1]] == 0) ++cnt;
				visited[cur[0]][cur[1]] = true;
				
				int nr, nc;
				for(int d = 0; d < 4; d++) {
					nr = cur[0] + dr[d];
					nc = cur[1] + dc[d];
					if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != -1) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
			}
			if(q.size() > 0)
				++days;
		}
		if(cnt == tomato) return days;
		else return -1;
	}

	
	
	
	
	
	
	
	
	
	
}
