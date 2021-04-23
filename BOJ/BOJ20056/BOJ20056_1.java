package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * BOJ20056 마법사 상어의 파이어볼
 * 주의 ! 속도를 받을 때 나눠서 받지 말것 -> 겹친 파이어볼 처리에서 오류가 남 -> 이동 처리 해줄 때만 mod처리
 * 파이어볼의 list를 만들어서 푼 버전
 * 메모리 : 32900kb 시간 : 1820ms
 * @author 미령
 *
 */
public class BOJ20056_1 {
	static int N, M, K;
	static LinkedList<Ball> map[][], bList;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] even = { 0, 2, 4, 6 };
	static int[] odd = { 1, 3, 5, 7 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new LinkedList[N][N];
		bList = new LinkedList<Ball>(); // 파이어볼 리스트
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<Ball>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			bList.add(new Ball(r, c, m, s, d));
		}

		for (int k = 0; k < K; k++) {
			for (Ball b : bList) {
				move(b); // 리스트에서 이동 처리
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() <= 1) {
						map[i][j].clear(); // 리스트에 정보 다 저장 되있으므로 map에는 2개 이상 저장되어있는 정보만 확인하면 됨
						continue;
					}
					setBall(i, j);
				}
			}
		}
		System.out.println(count());
	}

	private static void move(Ball b) {
		int nr, nc;
		nr = (b.r + dr[b.d] * (b.s % N) + N) % N; // 이동처리
		nc = (b.c + dc[b.d] * (b.s % N) + N) % N;
		b.r = nr;
		b.c = nc;
		map[nr][nc].add(b);
	}

	private static void setBall(int r, int c) {
		int m = 0, s = 0, d = 0, size = map[r][c].size();
		int[] dir;
		for (Ball b : map[r][c]) {
			m += b.m;
			s += b.s;
			d += b.d % 2;
			bList.remove(b); //리스트에서 지워주고
		}
		map[r][c].clear();
		m /= 5;
		if (m == 0) {
			return;
		}
		s /= size;
		if (d == 0 || d == size)
			dir = even;
		else
			dir = odd;

		d = 0;
		for (int i = 0; i < 4; i++) {
			bList.add(new Ball(r, c, m, s, dir[i])); //다시 리스트에 추가
		}
	}

	private static int count() {
		int ans = 0;
		for (Ball b : bList)
			ans += b.m;
		return ans;
	}

	static class Ball {
		int r, c, m, s, d;

		public Ball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}
}
