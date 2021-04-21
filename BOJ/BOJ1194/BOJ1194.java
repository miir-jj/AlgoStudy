package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1194 달이 차오른다, 가자.
 * 비트마스크로 visit체크가 포인트(열쇠관점)
 * @author 미령
 *
 */
public class BOJ1194 {
	static int N, M, sr, sc, ans = -1;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '0') {
					sr = i;
					sc = j;
				}
			}
		}
		bfs();
		System.out.println(ans);
	}

	private static void bfs() {
		boolean[][][] visited = new boolean[N][M][1 << 7]; //열쇠 획득시 마다 visit체크
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { sr, sc, 0, 0 }); // 행 열 이동횟수 키
		visited[sr][sc][0] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (map[cur[0]][cur[1]] == '1') {
				ans = cur[2];
				return;
			}
			int nr, nc, nv;
			for (int d = 0; d < 4; d++) {
				nr = cur[0] + dr[d];
				nc = cur[1] + dc[d];
				nv = cur[3];
				if (isIn(nr, nc)) {
					if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') { // 열쇠
						nv |= (1 << map[nr][nc] - 'a'); // 열쇠 획득 체크
					} else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') { // 문
						if ((nv & (1 << (map[nr][nc] - 'A'))) == 0) // 열쇠 가지고 있지 않을 경우
							continue;
					}
					if(visited[nr][nc][nv]) continue; 
					visited[nr][nc][nv] = true; //방문체크
					q.offer(new int[] { nr, nc, cur[2] + 1, nv });
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != '#';
	}

}
