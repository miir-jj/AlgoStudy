package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 15683 감시
 * 갈 수 있는 방향 수 세놓고
 * 각cctv의 번호에 따라 list에 갈 수 있는 방향 저장
 * @author 미령
 *
 */

public class BOJ15683 {
	static int N, M, map[][], tmap[][], min = Integer.MAX_VALUE;
	static ArrayList<CCTV> cctv;
	static int[] dr = { -1, 0, 1, 0 }; // 상 좌 하 우
	static int[] dc = { 0, -1, 0, 1 };
	static int[] turn = { 4, 2, 4, 4, 1 }; // 갈 수 있는 방향의 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tmap = new int[N][M];
		cctv = new ArrayList<CCTV>(); //cctv 정보 리스트

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					cctv.add(new CCTV(i, j, map[i][j] - 1));
				}
			}
		}
		setDir(0);
		System.out.println(min);
	}

	private static void setDir(int idx) {
		if (idx == cctv.size()) {
			// 사각지대 체크
			check();
			int cnt = count();
			min = min < cnt ? min : cnt;
			return;
		}
		int c = cctv.get(idx).num; //idx = n번째 cctv
		for (int i = 0, end = turn[c]; i < end; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			switch (c) {
			case 0:
				list.add(i);
				break;
			case 1: //2번 cctv일때는 서로 반대방향
				list.add(i);
				list.add(i + 2);
				break;
			case 2: //3번일때는 직각방향
				list.add(i);
				list.add(i + 1);
				break;
			case 3: //4번일때는 하나빼고
				list.add(i);
				list.add(i + 1);
				list.add(i + 2);
				break;
			case 4://5번은 모두
				list.add(i);
				list.add(i + 1);
				list.add(i + 2);
				list.add(i + 3);
				break;
			}
			cctv.get(idx).list.clear();
			cctv.get(idx).list.addAll(list);
			setDir(idx + 1);
		}
	}

	private static void check() {
		for (int i = 0; i < N; i++) {
			tmap[i] = Arrays.copyOf(map[i], M);
		}

		for (int i = 0, end = cctv.size(); i < end; i++) {
			for (int d : cctv.get(i).list) {
				d = d % 4; 
				int nr = cctv.get(i).r;
				int nc = cctv.get(i).c;
				while (true) {
					nr += dr[d];
					nc += dc[d];
					if (!isIn(nr, nc))
						break;
					tmap[nr][nc] = -1;
				}
			}
		}
	}

	private static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmap[i][j] == 0)
					++cnt;
			}
		}
		return cnt;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 6;
	}

	static class CCTV {
		int r, c, num;
		ArrayList<Integer> list;

		public CCTV(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			list = new ArrayList<Integer>();
		}

	}
}
