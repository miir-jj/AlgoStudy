package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA8382 {
	/**
	 * 방향전환 D4
	 */

	static int min;
	static Pair p1, p2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine().trim());
		p1 = new Pair();
		p2 = new Pair();
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			bw.write("#" + t + " ");
			st = new StringTokenizer(br.readLine().trim(), " ");
			p1.c = Integer.parseInt(st.nextToken()) + 100;
			p1.r = Integer.parseInt(st.nextToken()) + 100;
			p2.c = Integer.parseInt(st.nextToken()) + 100;
			p2.r = Integer.parseInt(st.nextToken()) + 100;

			bfs();
			bw.write(min + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static void bfs() {
		Queue<Pair> queue = new LinkedList<Pair>();
		boolean visited[][][] = new boolean[201][201][2];
		int[] d = { 0, 1 };// i/2=0 | 1
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		queue.offer(new Pair(p1.r,p1.c,0,0));
		queue.offer(new Pair(p1.r,p1.c,0,1));
		visited[p1.r][p1.c][0] = true;
		visited[p1.r][p1.c][1] = true;

		Pair cur;
		int nr,nc;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			if (cur.r == p2.r && cur.c == p2.c) {
				min = cur.cnt;
				break;
			}
			if(cur.dir==0) {
				for (int i = 2; i < 4; i++) {
					nr = cur.r + dr[i];
					nc = cur.c + dc[i];
					if (nr >= 0 && nc >= 0 && nr < 201 && nc < 201&&!visited[nr][nc][1]) {
						visited[nr][nc][1]=true;
						queue.offer(new Pair(nr,nc,cur.cnt+1,1));
					}
				}
			}else if(cur.dir==1) {
				for (int i = 0; i < 2; i++) {
					nr = cur.r + dr[i];
					nc = cur.c + dc[i];
					if (nr >= 0 && nc >= 0 && nr <= 201 && nc <= 201&&!visited[nr][nc][0]) {
						visited[nr][nc][0]=true;
						queue.offer(new Pair(nr,nc,cur.cnt+1,0));
					}
				}
			}
		}
	}
	static class Pair {
		int r, c, cnt, dir;

		public Pair() {}
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public Pair(int r, int c, int cnt, int d) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = d;
		}

	}

}
