package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 20057 마법사 상어와 토네이도
 * 토네이도 구현은 어렵지 않았음
 * 먼지 퍼뜨리는게 까다로웠음 -> 노가다로 하면 코드가 너무 길어짐
 * 최대한 반복문 사용하려고 노력
 * 
 * @author 미령
 *
 */
public class BOJ20057 {
	static int N, map[][];
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	static double[] ratio = { 0.01, 0.07, 0.1 }; // 구간 별 먼지 이동 비율
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int total = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j]; // 전체 먼지양 구해놓음
			}
		}
		
		move(); //토네이도
		System.out.println(count(total)); // 전체 먼지양에서 남아있는 먼지양 빼기 -> 격자를 벗어난 먼지양
		
	}
	private static int count(int total) {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				cnt += map[i][j];
			}
		}
		return total - cnt;
	}
//	토네이도 구현
	private static void move() {
		int r = N/2, c = N/2, len = 1, cnt = 0; // 시작점은 중앙, N은 홀수
		
//		두 방향 이동 후 길이 증가함, (0,0)도달 시 토네이도 소멸
		while(true) {
			for(int d = 0; d < 4; d++) {
				for(int l = 0; l < len; l++) {
					r += dr[d];
					c += dc[d];
					spread(r, c, d); // 먼지 퍼뜨리기
					if(r == 0 && c == 0) return;
				}
				++cnt;
				if(cnt == 2) { // 두방향 돌았을 때 길이 증가
					cnt = 0;
					++len;
				}
			}
		}
	}
//	먼지 퍼뜨리기
	private static void spread(int x, int y, int d) {
		int[] dir = { (d + 3) % 4, (d + 5) % 4 }; // 현재 방향을 기준으로 수직방향을 구함
		int r = x - dr[d]; // 한칸 전부터 시작
		int c = y - dc[d];
		
		int nr, nc, dust, total = map[x][y];
		for(int l = 0; l < 3; l++) { // 3 열에 걸쳐서
			nr = r + dr[d] * l; // 행 맞추기
			nc = c + dc[d] * l;
			dust = (int) (total * ratio[l]); // 이동할 먼지 양
			int nnr, nnc; 
			for(int d2 = 0; d2 < 2; d2++) {
				nnr = nr + dr[dir[d2]]; // 열맞추기
				nnc = nc + dc[dir[d2]];
				setMap(x, y, nnr, nnc, dust); //좌표 안이면 이동된 먼지 표시, 기준점 먼지 양 감소시킴
			}
		}
		
		dust = (int) (total * 0.02);
		for(int d2 = 0; d2 < 2; d2++) {
			nr = x + dr[dir[d2]] * 2;
			nc = y + dc[dir[d2]] * 2;
			setMap(x, y, nr, nc, dust);
		}
		
		nr = x + dr[d] * 2;
		nc = y + dc[d] * 2;
		dust = (int) (total * 0.05);
		setMap(x, y, nr, nc, dust);
		
		if(isIn(x + dr[d], y + dc[d]))
			map[x + dr[d]][y + dc[d]] += map[x][y]; // 좌표 안이면 먼지 증가시킴
		map[x][y] = 0; // 기준점은 0처리
	}
//	좌표가 map안이면 먼지 이동시키고 기준점의 먼지는 감소시킴
	private static void setMap(int x, int y, int nr, int nc, int dust) {
		if(isIn(nr, nc)) {
			map[nr][nc] += dust;
		}
		map[x][y] -= dust;
	}
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
