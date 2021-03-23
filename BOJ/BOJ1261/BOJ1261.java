package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1261 알고스팟 시작위치(0,0) 도착위치(N-1,M-1)
 */
public class BOJ1261 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		boolean[][] visited = new boolean[N][M];
		int min = Integer.MAX_VALUE;
		PriorityQueue<Cell> pq = new PriorityQueue<Cell>();
		pq.offer(new Cell(0, 0, 0));

		Cell cur = null;
		int nr, nc;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			
			if(cur.cnt>min) continue;
			if(cur.r==N-1&&cur.c==M-1) {
				min=min<cur.cnt?min:cur.cnt;
				continue;
			}
			
			for (int d = 0; d < 4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && !visited[nr][nc]) {
					visited[nr][nc]=true;
					if(map[nr][nc]==0) pq.offer(new Cell(nr,nc,cur.cnt));
					else pq.offer(new Cell(nr,nc,cur.cnt+1));
				}
			}
		}
		System.out.println(min);
	}

	static class Cell implements Comparable<Cell>{
		int r, c, cnt;

		public Cell(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Cell o) {
			return this.cnt-o.cnt;
		}
		
	}
}
