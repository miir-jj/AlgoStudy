package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 20058 마법사 상어와 파이어스톰
 * @author 미령
 *
 */
public class BOJ20058 {
	static int N, Q, L, map[][], tmap[][], calN, calL, ice, size;
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };
	static boolean visited[][];
	static Stack<int[]> stack;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		calN = 1 << N;
		map = new int[calN][calN];
		visited = new boolean[calN][calN];
		stack = new Stack<int[]>();
		for (int i = 0; i < calN; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < calN; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			L = Integer.parseInt(st.nextToken()); // 자를 수 가져오기
			calL = 1 << L;
			tmap = new int[calL][calL]; // 잘라서 회전시킨 배열 저장

			if (calL != 0) {
				for (int r = 0; r < calN; r += calL) { // L사이즈로 나누기
					for (int c = 0; c < calN; c += calL) {
						roll(r, c); // 시계방향 회전
					}
				}
			}
			for (int r = 0; r < calN; r++) { // 얼음 양 체크
				for (int c = 0; c < calN; c++) {
					if (map[r][c] > 0)
						check(r, c);
				}
			}
			// 얼음 양은 동시에 줄어들어야 하므로 0이 되는 부분은을 저장해뒀다가 한번에 0으로 변경
			// => 바뀌는부분 다 저장했다가 변경해줘도 되겠다
			while(!stack.isEmpty()) {
				int[] cur = stack.pop();
				--map[cur[0]][cur[1]];
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder()); // 덩어리 크기 저장할 pq
		for (int k = 0; k < calN; k++)
			Arrays.fill(visited[k], false); // visited배열 초기화
		for (int i = 0; i < calN; i++) {
			for (int j = 0; j < calN; j++) {
				if (!visited[i][j] && map[i][j] > 0) { // 방문전이고, 얼음이 있는 칸일 경우
					pq.add(bfs(i, j)); // bfs에서 센 칸 수를 pq에 추가
				}
			}
		}
		if (pq.size() > 0)
			size = pq.poll(); // 가장 큰 덩어리 크기를 size에 대입
		System.out.println(ice); // 남은 얼음 양
		System.out.println(size); // 가장 큰 덩어리 크기
	}

	private static int bfs(int i, int j) {
		int cnt = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { i, j });
		visited[i][j] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			ice += map[cur[0]][cur[1]]; // 남아있는 얼음양 세기
			++cnt; // 덩어리 크기 세기

			int nr, nc;
			for (int d = 0; d < 4; d++) {
				nr = cur[0] + dr[d];
				nc = cur[1] + dc[d];
				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] > 0) {
					q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}
		}
		return cnt;
	}

	private static void check(int r, int c) {
		int nr, nc, cnt = 0;
		for (int d = 0; d < 4; d++) { // 사방탐색
			nr = r + dr[d];
			nc = c + dc[d];
			if (isIn(nr, nc) && map[nr][nc] > 0)
				++cnt; // 얼음이 있는 칸일경우 +1
		}
		if (cnt < 3) { // 얼음있는 인접한 칸이 3개보다 작을경우
			stack.push(new int[] { r, c }); // 스택에 저장 -> 동시에 처리되어야 하므로 저장 후 한꺼번에 처리
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < calN && c >= 0 && c < calN;
	}

	private static void roll(int r, int c) {
		for (int i = 0; i < calL; i++) {
			for (int j = 0; j < calL; j++) {
//				 시계방향 90도 회전시키는 알고리즘 -> (i,j) -> (j, size - i), 시계 반대방향 -> (i, j) -> (size - j, i)
				tmap[j][calL - 1 - i] = map[i + r][j + c]; // (0,0)이 시작점이라고 두고 calL * calL크기의 배열에 회전시켠 배열 값 대입
			}
		}
		for (int i = 0; i < calL; i++) {
			for (int j = 0; j < calL; j++) {
				map[i + r][j + c] = tmap[i][j]; // 원래 배열에 다시 대입해줌 => 시작점 r,c로 변경
			}
		}
	}

}
