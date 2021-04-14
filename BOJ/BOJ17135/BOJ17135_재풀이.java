package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17135_재풀이 {
	static int N, M, D, enemy, max, map[][], archers[];
	static int[] dr = { 0, -1, 0 };
	static int[] dc = { -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M];
		archers = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					++enemy;
			}
		}
		setArcher(0, 0, 0);
		System.out.println(max);
	}

	private static void setArcher(int idx, int start, int flag) {
		if (idx == 3) {
			move();
			return;
		}
		for (int i = start; i < M; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			archers[idx] = i;
			setArcher(idx + 1, start + 1, flag | 1 << i);
		}
	}

	private static void move() {
		int n = N;
		int kill = 0;
		int[][] tmap = new int[N + 1][M];
		for(int i=0;i<N;i++) {
			System.arraycopy(map[i], 0, tmap[i], 0, M);
		}
		Queue<int[]> q = new LinkedList<int[]>();
		ArrayList<int[]> target = new ArrayList<int[]>(3);

		while (n > 0) {
			for (int i = 0; i < 3; i++) {
				q.offer(new int[] { n, archers[i], 0 });

				while (!q.isEmpty()) {
					int[] cur = q.poll();
					if (tmap[cur[0]][cur[1]] == 1) {
						target.add(new int[] { cur[0], cur[1] });
						q.clear();
						break;
					}
					int nr, nc;
					for (int d = 0; d < 3; d++) {
						nr = cur[0] + dr[d];
						nc = cur[1] + dc[d];
						if (nr >= 0 && nr < n && nc >= 0 && nc < M && cur[2] < D) {
							q.offer(new int[] { nr, nc, cur[2] + 1 });
						}
					}
				}
			}
			for (int i = 0, end = target.size(); i < end; i++) {
				if (tmap[target.get(i)[0]][target.get(i)[1]] == 1) {
					tmap[target.get(i)[0]][target.get(i)[1]] = 0;
					++kill;
				}
			}
			target.clear();
			--n;
		}
		max = max > kill ? max : kill;
	}
}
