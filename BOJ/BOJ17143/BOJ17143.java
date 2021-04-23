package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 17143 낚시왕
 * **상어 맵 벗어났을 때 방향 전환 위해 방향 숫자를 바꿔줌
 * **속도가 최대 1000이기 때문에 방향에 따라 행길이, 열길이로 속도를 나눠줌
 * 1. 낚시왕 한칸 이동
 * 2. 상어 잡기
 * 3. 겹친 상어 잡아먹힘
 * 	- 맵에 있는 상어와 현재 상어 크기 비교해줌
 *  - 지워야 되는 상어 저장했다가 한번에 처리
 * @author 미령
 *
 */
public class BOJ17143 {
	static int R, C, M, fisher, ans;
	static LinkedList<Shark> sList;
	static Shark[][] map;
	static int[] dr = { 0, -1, 0, 0, 1 };
	static int[] dc = { 0, 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] dir = { 0, 1, 4, 3, 2 }; //방향전환을 위해 방향숫자 조정 -> 상1 / 좌2 / 우3 / 하4
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sList = new LinkedList<Shark>(); //상어 리스트
		map = new Shark[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = dir[Integer.parseInt(st.nextToken())]; //방향 조정 후 대입
			int z = Integer.parseInt(st.nextToken());

			if (s != 0) { //속도가 0이 아닐 때
				if (d == 1 || d == 4) // 상하 방향이면
					s %= ((R << 1) - 2); //행길이로 나눠줌
				if (d == 2 || d == 3) // 좌우 방향이면
					s %= ((C << 1) - 2); //열길이로 나눠줌
			}
			Shark shark = new Shark(r, c, s, d, z);
			map[shark.r][shark.c] = shark; // 맵에 표시
			sList.add(shark); // 상어 리스트에 추가
		}

		for (int i = 0; i < C; i++) {
			++fisher; //낚시왕 한칸 옆으로
			getShark(); //상어 잡고
			moveShark(); //상어 이동
			killShark(); //위치 겹치면 잡아먹힘
		}
		System.out.println(ans);
	}
//	상어 잡는 함수
	private static void getShark() {
		Shark rm = null; //잡을 상어 변수
		for(int r = 1; r <= R; r++) { //행을 돌며
			if(map[r][fisher] != null) { //맵에 상어가 있을 때
				rm = map[r][fisher]; // 해당상어 변수에 대입
				break; //반복문 빠져나옴
			}
		}
		if(rm != null) { //잡을 상어가 있을 경우
			ans += rm.z; //사이즈 더해줌
			sList.remove(rm); //잡은 상어 상어 리스트에서 삭제
		}
	}
//	상어 이동함수
	private static void moveShark() { 
		for (Shark s : sList) {
			int nr = s.r, nc = s.c;
			for (int i = 0; i < s.s; i++) {
				nr += dr[s.d]; //해당 방향으로 이동
				nc += dc[s.d]; 
				if (!isIn(nr, nc)) { // 맵을 벗어나면
					nr -= dr[s.d] * 2; // 전전단계로 이동시키고
					nc -= dc[s.d] * 2;
					s.d = 5 - s.d; // 상 - 하, 좌 - 우 반전시킨 방향으로 변환
				}
			}
			s.r = nr; //상어 위치에 바뀐 위치 대입
			s.c = nc;
		}
	}
//	겹친 상어 처리 함수
	private static void killShark() {
		for (int i = 1; i <= R; i++) {
			Arrays.fill(map[i], null); //맵 초기화
		}
		Stack<Shark> rList = new Stack<Shark>(); //지워줄 상어
		for (Shark s : sList) {
			if (map[s.r][s.c] != null) { //상어 위치가 비어있지 않을 때
				if (map[s.r][s.c].z < s.z) { //맵에 있는 상어 크기보다 크면
					rList.push(map[s.r][s.c]); // 맵에 있는 상어를 스택에 추가
					map[s.r][s.c] = s; //현재 상어로 바꿔줌
				}else {
					rList.push(s); // 현재 상어 크기가 더 작으면 현재 상어를 스택에 추가
				}
				continue;
			} 
			map[s.r][s.c] = s; // 비어있으면 현재 상어 대입
		}
		while(!rList.isEmpty()) {
			sList.remove(rList.pop());
		}
	}


	private static boolean isIn(int nr, int nc) {
		return nr > 0 && nr <= R && nc > 0 && nc <= C;
	}
	
	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r; // 행
			this.c = c; // 열
			this.s = s; // 속력
			this.d = d; // 방향
			this.z = z; // 크기
		}

		@Override
		public int compareTo(Shark o) {
			if (this.r == o.r)
				return o.z - this.z;
			return this.r - o.r;
		}

	}
}
