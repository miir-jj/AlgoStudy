package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 17779 게리맨더링2
 * 1. 가능한 d1, d2를 뽑는다
 * 2. 그 때 기준점 x, y 경우를 구한다
 * 3. 5번선거구 경계선을 표시한다
 * 4. 1~4번 기준점을 표시한다
 * 5. bfs를 돌며 각 선거구를 표시(5번 제외)
 * 6. 각선거구 인구수를 세서 차이 최소를 구함
 * @author 미령
 *
 */
public class BOJ17779 {
	static int N, d1, d2, diff, pop[], map[][], section[][];
	static int[] dr = { -1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		section = new int[N][N];
		pop = new int[6];
		diff = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		setDist(); //d1, d2 따지기
		System.out.println(diff);
	}

	private static void bfs(int r, int c, int loc) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		section[r][c] = loc;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int nr, nc;
			for(int d = 0; d < 4; d++) {
				nr = cur[0] + dr[d];
				nc = cur[1] + dc[d];
				if(canMove(nr, nc, loc)) {
					section[nr][nc] = loc; //해당 선거구로 표시
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}

	private static boolean canMove(int r, int c, int loc) {
		if(r < 0 || r >= N || c < 0 || c >= N)
			return false;
		if(section[r][c] != 0) // 0인 곳은 5번 선거구로 들어감
			return false;
		return true;
	}
	
	private static void count() {
		int max = 0, min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				pop[section[i][j]] += map[i][j];
			}
		}
		pop[5] += pop[0];
		
		for(int i = 1; i <= 5; i++) {
			max = max > pop[i] ? max : pop[i];
			min = min < pop[i] ? min : pop[i];
		}
		diff = diff < max - min ? diff : max - min;		
	}

	private static void check(int x, int y) {
		for(int i = 0; i < N; i++) { // 선거구 표시배열 초기화
			Arrays.fill(section[i], 0);
		}
		Arrays.fill(pop, 0);	// 인구 배열 초기화
		
		// 경계선 표시
		for (int i = 0; i <= d1; i++) {	
			section[x + i][y - i] = 5;
			section[x + d2 + i][y + d2 - i] = 5;
		}
		for (int i = 0; i <= d2; i++) {
			section[x + i][y + i] = 5;
			section[x + d1 + i][y - d1 + i] = 5;
		}
		
		//각 선거구 기준점 표시
		for (int r = 0; r < x; r++)
			section[r][y] = 1;
		
		for (int r = x + d1 + d2 + 1; r < N; r++)
			section[r][y - d1 + d2] = 4;
		
		for (int c = 0; c < y - d1; c++)
			section[x + d1][c] = 3;
		
		for (int c = y + d2 + 1; c < N; c++)
			section[x + d2][c] = 2;
		
		//각 선거구 표시
		bfs(0, 0, 1);
		bfs(0, N - 1, 2);
		bfs(N -1, 0, 3);
		bfs(N - 1, N - 1, 4);

		count(); //인구 세기
	}


	private static void setPoint() { // 가능한 모든 기준점 구하기
		for (int i = 0; i + d1 + d2 < N; i++) {
			for (int j = d1; j < N - d2; j++) {
				check(i, j);
			}
		}
	}

	private static void setDist() { //가능한 모든 d1, d2를 구함
		for (int i = 2; i < N; i++) { // d1, d2 >=1 이고 d1 + d2 < N
			for (int j = 1; i - j >= 1; j++) {
				d1 = j;
				d2 = i - j;
				setPoint(); //기준점 정하기
			}
		}

	}
}
