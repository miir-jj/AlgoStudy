package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 12100 2048(Easy)
 * 5번 이동시킬 수 있는 모든 경우의 수 구함 -> 중복순열
 * 각 방향에 대해서 시작점과 다음 행 혹은 열로 넘어가게 될 값을 설정해줌
 * 맵을 넘어가지 않을 때 까지 각 열/행을 검사
 * 다섯번 모두 이동한 후 최댓값 구하기
 * @author 미령
 *
 */
public class BOJ12100 {
	static int N, max, map[][], tmap[][], dir[], sr, sc, nr, nc;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		tmap = new int[N][N];
		dir = new int[5];
		q = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getDir(0);
		System.out.println(max);
	}

	private static void getDir(int idx) { // 5번 이동시킬 방향 구하기
		if (idx == 5) {
			setMap();
			return;
		}
		for (int i = 0; i < 4; i++) {
			dir[idx] = i;
			getDir(idx + 1);
		}

	}

	private static void setMap() {
		for(int i = 0; i < N; i++) // 임시배열 초기화
			System.arraycopy(map[i], 0, tmap[i], 0, N);
		for (int i = 0; i < 5; i++) {
			init(dir[i]); // 현재 방향 설정
			while (isIn(sr, sc)) { // 시작점이 맵을 넘지 않았을 때
				int before = 0; // 초기값 0
				int r, c;
				for (int j = 0; j < N; j++) {
					r = sr + dr[dir[i]] * j; //다음행/열을 차례로 검사
					c = sc + dc[dir[i]] * j;
					if (tmap[r][c] == 0) // 0이면 스킵
						continue;
					if (before == 0) // 전값이 0이면 -> 처음이거나 두칸의 값이 같아 큐에 들어간 경우
						before = tmap[r][c]; // 현재 값으로 설정해줌
					else {
						if (before == tmap[r][c]) { // 전 값과 현재 값이 같으면
							q.offer(before + before); // 큐에 더한값 삽입
							before = 0; // 전 값 초기화
						} else {
							q.offer(before); // 같지 않으면 전 값 큐에 삽입
							before = tmap[r][c]; // 현재값으로 초기화
						}
					}
					tmap[r][c] = 0; // 맵 초기화
				}
				if (before != 0) //마지막 검사 값 남아있을 경우
					q.offer(before); //큐에 넣어줌
				int j = 0;
				while (!q.isEmpty()) { // 큐에 있는 값 시작점부터 차례로 넣어주기
					r = sr + dr[dir[i]] * j;
					c = sc + dc[dir[i]] * j++;
					tmap[r][c] = q.poll();
				}
				sr += nr; //다음 행/열로 넘어감
				sc += nc;
			}
		}
		getMaxNum();
	}

	private static void getMaxNum() { // 현재 맵에서 최댓값 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = max > tmap[i][j] ? max : tmap[i][j];
			}
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static void init(int i) {
		switch (i) {
		case 0: // 아래로 내렸을 때
			nr = 0;
			nc = 1;
			sr = N - 1;
			sc = 0;
			break;
		case 1: // 위로 올렸을 때
			nr = 0;
			nc = 1;
			sr = 0;
			sc = 0;
			break;
		case 2: // 오른쪽으로 밀었을 때
			nr = 1;
			nc = 0;
			sr = 0;
			sc = N - 1;
			break;
		case 3: // 왼쪽으로 밀었을 때
			nr = 1;
			nc = 0;
			sr = 0;
			sc = 0;
			break;
		}

	}

}
