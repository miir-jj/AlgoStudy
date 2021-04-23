package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * BOJ20056 마법사 상어의 파이어볼
 * 주의 ! 속도를 받을 때 나눠서 받지 말것 -> 겹친 파이어볼 처리에서 오류가 남 -> 이동 처리 해줄 때만 mod처리
 * 2차원 배열 두개로 푼 버전 메모리는 더들었지만 시간은 1/3 
 * 메모리 : 83832kb 시간 : 600ms
 * @author 미령
 *
 */
public class BOJ20056 {
	static int N, M, K;
	static LinkedList<Ball> map[][], tmap[][];
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] even = { 0, 2, 4, 6};
	static int[] odd = { 1, 3, 5, 7 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new LinkedList[N][N]; //저장받을 배열
		tmap = new LinkedList[N][N]; //이동하고, 겹친 부분 처리해줄 배열
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<Ball>();
				tmap[i][j] = new LinkedList<Ball>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken()); //질량
			int s = Integer.parseInt(st.nextToken()); //속도
			int d = Integer.parseInt(st.nextToken()); //거리
			map[r][c].add(new Ball(m, s, d));
		}
		
		for(int k = 0; k < K; k++) { // K번 실행
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j].size() > 0) //위치에 파이어 볼이 있을 때
					move(i, j); //이동
				}
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(tmap[i][j].size() > 1) //해당 위치에 파이어볼이 2개 이상 있을 때
						setBall(i, j); //겹침 처리
				}
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j].addAll(tmap[i][j]); //tmap을 map으로 복사
					tmap[i][j].clear(); //tmap 초기화
				}
			}
		}
		System.out.println(count()); //남은 파이어볼 세주기
	}

	private static int count() {
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(Ball b : map[i][j])
					ans += b.m;
			}
		}
		return ans;
	}

	private static void setBall(int r, int c) {
		int m = 0, s = 0, d = 0;
		int[] dir;
		for(Ball b : tmap[r][c]) {
			m += b.m; //질량, 속도 합
			s += b.s;
			d += b.d % 2; // 방향 모두 짝인지 홀인지 판별 위함
		}
		m /= 5; 
		if(m == 0) { //질량이 0인 파이어볼은 소멸한다
			tmap[r][c].clear();
			return;
		}
		s /= tmap[r][c].size(); 
		if(d == 0 || d == tmap[r][c].size())  //size 만큼의 파이어볼이 모두 짝수거나 홀수이거나
			dir = even;
		else
			dir = odd;
		
		tmap[r][c].clear(); //tmap 초기화
		for(int i = 0; i < 4; i++) {
			tmap[r][c].add(new Ball(m, s, dir[i])); // 4개로 쪼개진 파이어볼 저장
		}
	}

	private static void move(int r, int c) {
		int nr, nc;
		for(Ball b : map[r][c]) {
			nr= r + dr[b.d] * (b.s % N) + N; // 속도 최댓값 1000이므로 나눠서 처리, 마이너스 대비해 N더해줌
			nc= c + dc[b.d] * (b.s % N) + N; // N에서 넘어가거나 1에서 넘어가면 다시 1과 N으로 각각 돌아오기 때문
			if(nr >= N ) nr %= N; // 맵 넘어갔을 때 대비 처리
			if(nc >= N ) nc %= N;
			tmap[nr][nc].add(b); // 임시 배열에 저장
		}
		map[r][c].clear(); //원래 배열 초기화
	}

	static class Ball {
		int m, d, s;

		public Ball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
