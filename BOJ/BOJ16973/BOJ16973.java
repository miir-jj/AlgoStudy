package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 16973 직사각형 탈출
 * @author 미령
 * 
 */
public class BOJ16973 {
	static int map[][], N, M, H, W, ans = -1;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

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
		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int[] start = { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };
		int[] end = { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };

		bfs(start, end);
		System.out.println(ans);
	}

	private static void bfs(int[] start, int[] end) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {start[0], start[1], 0});
		map[start[0]][start[1]] = -1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == end[0] && cur[1] == end[1]) {
				ans = cur[2];
				return;
			}
			int nr, nc;
			for(int d = 0; d < 4; d++) {
				nr = cur[0] + dr[d];
				nc = cur[1] + dc[d];
				
				if(isIn(nr, nc) && map[nr][nc] == 0) {
					if(check(nr, nc, cur, d)) {
						map[nr][nc] = -1;
						q.offer(new int[] {nr, nc, cur[2] + 1});
					}
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M ;
	}

	private static boolean check(int row, int col, int[] cur, int d) {
		int r = row;
		int c = col;
		int dir, len;
		if (d / 2 < 1) {
			dir = 3;
			len = W;
			if (r > cur[0]) r += (H - 1);
		} else {
			dir = 1;
			len = H;
			if(c > cur[1]) c += (W - 1);
		}

		int nr, nc;
		for(int i = 0; i < len; i++) {
			nr = r + dr[dir] * i;
			nc = c + dc[dir] * i;
			if(!isIn(nr, nc) || map[nr][nc] > 0) return false;
		}
		return true;
	}

}
