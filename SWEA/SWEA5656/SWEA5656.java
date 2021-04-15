package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 미령 5656 벽돌깨기
 */
public class SWEA5656 {
	static int N, W, H, ans, total, map[][], tmap[][], marble[];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			bw.write("#" + t + " ");
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			tmap = new int[H][W];
			marble = new int[N];
			ans = Integer.MAX_VALUE;
			total = 0;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0)
						++total;
				}
			}
			setMarble(0);
			bw.write(ans + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static void setMarble(int idx) {
		if (idx == N) {
			shoot();
			return;
		}
		for (int i = 0; i < W; i++) {
			marble[idx] = i;
			setMarble(idx + 1);
		}
	}

	private static void shoot() {
		for (int i = 0; i < H; i++) {
			System.arraycopy(map[i], 0, tmap[i], 0, W);
		}
		int cnt = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[H][W];
		for (int n = 0; n < N; n++) {
			int r = 0;
			for (int i = 0; i < H; i++) {
				if (tmap[i][marble[n]] != 0) {
					r = i;
					break;
				}
			}
			q.offer(new int[] { r, marble[n], tmap[r][marble[n]] - 1 });
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int nr, nc;
				for (int d = 0; d < 4; d++) {
					for (int l = 0; l <= cur[2]; l++) {
						nr = cur[0] + dr[d] * l;
						nc = cur[1] + dc[d] * l;
						if (nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc] && tmap[nr][nc] > 0) {
							q.offer(new int[] { nr, nc, tmap[nr][nc] - 1 });
							visited[nr][nc] = true;
							tmap[nr][nc] = 0;
							++cnt;
						}
					}
				}
			}
			reset();
			for (int i = 0; i < H; i++) {
				Arrays.fill(visited[i], false);
			}
		}
		ans = ans < total - cnt ? ans : total - cnt;
	}

	private static void reset() {
		Stack<Integer> s = new Stack<Integer>();
		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {
				if (tmap[i][j] != 0) {
					s.push(tmap[i][j]);
					tmap[i][j] = 0;
				}
			}
			int k = H;
			while (!s.isEmpty()) {
				tmap[--k][j] = s.pop();
			}
		}
	}
}
