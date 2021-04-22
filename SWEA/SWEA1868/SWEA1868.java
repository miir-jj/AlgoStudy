package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * 1868 파핑파핑 지뢰찾기
 * 8방탐색 후 주위에 지뢰 없으면 bfs -> 갈 수 있는데 까지 가고 visit체크함
 * 연쇄로 터질 수 있는 곳 한번 다 돈 후
 * 1칸씩 터지는 칸들 수를 세어줌
 * 
 * @author 미령
 *
 */
public class SWEA1868 {
	static int N, dot;
	static int[] dr = { -1, -1, -1, 1, 1, 1, 0, 0 };
	static int[] dc = { -1, 0, 1, -1, 0, 1, -1, 1 };
	static char[][] map;
	static boolean[][] visited;
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		pq = new PriorityQueue<int[]>((a, b) -> b[2] - a[2]);

		for (int t = 1, end = Integer.parseInt(br.readLine()); t <= end; t++) {
			bw.write("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			dot = 0;
			map = new char[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' && !visited[i][j]) {
						if (check(i, j))
							++ans;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' && !visited[i][j])
						++ans;
				}
			}
			bw.write(ans + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static boolean check(int i, int j) {
		int nr, nc;
		for (int d = 0; d < 8; d++) {
			nr = i + dr[d];
			nc = j + dc[d];
			if (isIn(nr, nc) && !visited[nr][nc]) {
				if (map[nr][nc] == '*') {
					return false;
				}
			}
		}
		bfs(i, j);
		return true;
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;

		ArrayList<int[]> list = new ArrayList<int[]>();
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			int nr, nc;
			for (int d = 0; d < 8; d++) {
				nr = cur[0] + dr[d];
				nc = cur[1] + dc[d];
				if (isIn(nr, nc) && !visited[nr][nc]) {
					if (map[nr][nc] == '*') {
						list.clear();
						break;
					}
					list.add(new int[] { nr, nc });
				}
			}
			q.addAll(list);
			for (int[] l : list) {
				visited[l[0]][l[1]] = true;
			}
			list.clear();
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}
