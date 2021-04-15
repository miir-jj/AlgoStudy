package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * SWEA1953 
 * @author 미령
 * 시간당 1의 거리를 감(맨홀뚜껑 도달까지 1시간 이미 걸림)
 * 한 셀이 갈 수 있는 방향 셀마다 표시
 * check배열 -> 연결 가능성 표시 배열
 * visited 처리된 부분이 탈주범이 있을 수 있는 위치
 */
public class SWEA1953 {
	static int N, M, R, C, L;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] check = { 1, 0, 3, 2 };
	static Cell map[][];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int t = 1, end = Integer.parseInt(br.readLine()); t <= end; t++) {
			bw.write("#" + t + " ");
			init();
			bw.write(bfs() + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static int bfs() {
		Queue<Cell> q = new LinkedList<Cell>();
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;
		q.offer(map[R][C]);
		visited[R][C] = true;

		Cell c;
		while (!q.isEmpty()) {
			c = q.poll();
			if (c.time == L)
				continue;
			int nr, nc;
			for (int d : c.conn) {
				nr = c.r + dr[d];
				nc = c.c + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc].conn.contains(check[d])) {
					map[nr][nc].time = c.time + 1;
					q.offer(map[nr][nc]);
					visited[nr][nc] = true;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					++cnt;
			}
		}
		return cnt;
	}

	private static void init() throws Exception {
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new Cell[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = new Cell(i, j, 0);
				switch (Integer.parseInt(st.nextToken())) {
				case 1:
					map[i][j].conn.add(0);
					map[i][j].conn.add(1);
					map[i][j].conn.add(2);
					map[i][j].conn.add(3);
					break;
				case 2:
					map[i][j].conn.add(0);
					map[i][j].conn.add(1);
					break;
				case 3:
					map[i][j].conn.add(2);
					map[i][j].conn.add(3);
					break;
				case 4:
					map[i][j].conn.add(0);
					map[i][j].conn.add(3);
					break;
				case 5:
					map[i][j].conn.add(1);
					map[i][j].conn.add(3);
					break;
				case 6:
					map[i][j].conn.add(2);
					map[i][j].conn.add(1);
					break;
				case 7:
					map[i][j].conn.add(2);
					map[i][j].conn.add(0);
					break;
				}
			}
		}
		map[R][C].time = 1;
	}

	static class Cell {
		int r, c, time;
		ArrayList<Integer> conn;

		public Cell(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
			this.conn = new ArrayList<Integer>(2);
		}

	}

}
