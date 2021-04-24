package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 19238 스타트택시
 * 손님입장에서 택시까지의 거리를 구하면 시간초과남
 * 1. 택시에서 손님까지의 거리를 구함 -> 최단거리인 손님 구해서 반환
 * 2. 손님 도착지까지 이동
 * 3. 손님 삭제해줌
 * 4. 다시 최단거리 손님 찾음
 * @author 미령
 *
 */
public class BOJ19238 {
	static int N, M, P, SR, SC, map[][], dist[][]; // N 지도 길이 M 손님수 P 초기 연료량
	static ArrayList<Person> pList;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		dist = new int[N][N];
		pList = new ArrayList<Person>(M); //손님 리스트 관리

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 1은 벽, 2는 손님
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		SR = Integer.parseInt(st.nextToken()) - 1; //택시 출발지
		SC = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1, c = Integer.parseInt(st.nextToken()) - 1;
			pList.add(new Person(r, c, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));// 출발지, 도착지
			map[r][c] = 2; //손님 2
		}

		System.out.println(step());
	}

	private static int step() {
		while (pList.size() > 0) { //손님 남아있을 때 까지
			Person p = selectPerson();
			if (P == 0 || p == null) // 손님까지 못가고 연료가 바닥나서 못움직임
				return -1;
			int d = getDist(p.sr, p.sc, p.er, p.ec); // 목적지로 이동
			if (d < 0) // 목적지 이동 중 연료 떨어짐
				return -1;
			P += d; //목적지 이동 성공시 이동거리만큼 연료 추가
			SR = p.er; //현재 도착지로 출발지 재설정
			SC = p.ec;
		}
		return P;
	}
//	최단거리 손님 구하기
	private static Person selectPerson() {
		PriorityQueue<Person> person = new PriorityQueue<Person>(); // 최단거리 손님 구할pq
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); //최단거리 순으로 정렬
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		pq.offer(new int[] { 0, SR, SC }); //택시 출발지에서 시작
		dist[SR][SC] = 0;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (cur[0] > P) //현재까지의 거리가 연료를 넘었을 때
				break;
			if(map[cur[1]][cur[2]] == 2) { //손님위치일 때
				for(Person p : pList) {
					if(p.sr == cur[1] && p.sc == cur[2]) { //해당 손님 찾아서
						p.d = cur[0]; //거리 바꿔주고
						person.offer(p); //해당 손님 pq에 넣어줌
						break;
					}
				}
				continue; // 다음 위치로
			}
			int nr, nc;
			for (int d = 0; d < 4; d++) { //사방탐색
				nr = cur[1] + dr[d];
				nc = cur[2] + dc[d];
				if (isIn(nr, nc) && dist[nr][nc] > cur[0] + 1) { //격자 안이고, 이전에 갔던 거리보다 적게 가면
					dist[nr][nc] = cur[0] + 1; //바꿔주고
					pq.offer(new int[] { dist[nr][nc], nr, nc });
				}
			}
		}
		if(person.isEmpty()) return null; //최단거리 손님 없으면 리턴
		Person p = person.poll(); //최단거리 손님 대입
		pList.remove(p); //손님리스트에서 제거
		map[p.sr][p.sc] = 0; //맵에 제거
		P -= p.d; //연료 소진
		return p;
	}

	private static int getDist(int r, int c, int R, int C) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); //최단거리 순으로 정렬
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		pq.offer(new int[] { 0, r, c });
		dist[r][c] = 0;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (cur[0] > P) //연료 소진시
				return -1;
			if (cur[1] == R && cur[2] == C) //도착지일 때
				return cur[0];

			int nr, nc;
			for (int d = 0; d < 4; d++) {
				nr = cur[1] + dr[d];
				nc = cur[2] + dc[d];
				if (isIn(nr, nc) && dist[nr][nc] > cur[0] + 1) {
					dist[nr][nc] = cur[0] + 1;
					pq.offer(new int[] { dist[nr][nc], nr, nc });
				}
			}

		}
		return -1;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N && map[r][c] != 1;
	}

	static class Person implements Comparable<Person> {
		int d, sr, sc, er, ec;

		public Person(int sr, int sc, int er, int ec) {
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
			this.d = 0;
		}

		@Override
		public int compareTo(Person o) {
			if (this.d == o.d) {
				if (this.sr == o.sr)
					return this.sc - o.sc;
				return this.sr - o.sr;
			}
			return this.d - o.d;
		}
	}
}
