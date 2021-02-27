package BOJ0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ17135 {
	static int N, M, D;
	static int[] bow;
	static ArrayList<int[]> archer;
	static int[][] map;
	static int[] dr = { 0, -1, 0 }; // 좌 상 우 순서로 하 제외
	static int[] dc = { -1, 0, 1 };
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		bow = new int[M];
		archer=new ArrayList<>(M);
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, 0);
		for(int[] i:archer) {
			answer=Math.max(attack(i), answer);
		}
		System.out.println(answer);
	}

	// 궁수 위치 뽑기
	public static void comb(int cnt, int start) {
		if (cnt == 3) {
			archer.add(bow.clone());
			return;
		}
		for (int i = start; i < M; i++) {
			bow[i] = 1;
			comb(cnt + 1, i + 1);
			bow[i] = 0;
		}
	}

	// 적 공격
	public static int attack(int[] b) {
		int result=0;
		Queue<int[]> target = new LinkedList<int[]>(); // 공격대상후보큐
		boolean[][] attacked = new boolean[N][M]; // 공격대상
		int[][] copied = copyMap();
		for(int n=0;n<N;n++){
				
			for (int i = 0; i < M; i++) {
				if (b[i] == 1) {
					target.clear();
					target.offer(new int[] { N - 1, i }); // 궁수로부터 한칸 위 칸부터 적이 있는지 검사

					int[] tmp;
					int nr, nc;
					while (!target.isEmpty()) {
						tmp = target.poll(); // 공격대상후보
						if(getDistance(N, i, tmp[0], tmp[1])>D)
							break;
						if (copied[tmp[0]][tmp[1]] == 1) { // 적이면
							attacked[tmp[0]][tmp[1]] = true;// 공격대상에 넣고
							break; // 다음 궁수로
						} else {
							for (int j = 0; j < 3; j++) { // 좌 상 우 순으로
								nr = tmp[0] + dr[j];
								nc = tmp[1] + dc[j];
								if (nr > -1 && nr < N && nc > -1 && nc < M ) { // 맵을 넘지 않으면
									target.offer(new int[] { nr, nc });
								}
							}
						}
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(attacked[i][j]) {
						copied[i][j]=0;
						result++;
					}
				}
				Arrays.fill(attacked[i], false);
			}
			for(int i=N-1;i>0;i--) {
				copied[i]=Arrays.copyOf(copied[i-1], M);
			}
			Arrays.fill(copied[0], 0);

		}
		return result;
	}

	// 맵 카피
	public static int[][] copyMap() {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(map[i], M);
		}
		return copy;
	}

	public static int getDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2)+Math.abs(c1-c2);
	}
}
