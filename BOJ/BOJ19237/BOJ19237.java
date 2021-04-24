package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * 19237 어른상어
 * 입력관리 엄청 까다로웠던 문제
 * 상어 위치 체크 맵과 냄새관리 맵 따로 관리
 * shark 클래스, smell 클래스 따로 둠
 * 상어 방향에 따른 이동 우선순위 두어야함
 * 
 * @author 미령
 *
 */
public class BOJ19237 {
	static int N, M, K, priority[][][]; // 우선순위
	static Smell[][] sm; //냄새 관리 맵
	static ArrayList<Shark>[][] sh; //상어 위치 관리맵
	static LinkedList<Shark> sList; // 상어리스트
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sh = new ArrayList[N][N];
		sm = new Smell[N][N];
		priority = new int[M][4][4]; //M개의 상어 각각의 방향일 때의 우선순위
		sList = new LinkedList<Shark>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				sh[i][j] = new ArrayList<Shark>(); //맵 초기화
				sm[i][j] = new Smell(0, 0); //맵 초기화
				int n = Integer.parseInt(st.nextToken());
				if(n != 0) { // 상어이면
					sList.add(new Shark(n - 1, i, j)); // 상어리스트에 추가
					sh[i][j].add(sList.get(sList.size() - 1)); // 맵에 추가
					sm[i][j].num = n - 1; // 맨 처음 냄새뿌림
					sm[i][j].sec = K;
				}
			}
		}
		int[] dir = new int[M]; // 각각 상어의 현재 방향
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < M; i++) {
			dir[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		for(Shark s : sList) {
			s.d = dir[s.num]; // 상어에 대입해줌
		}
		for(int i = 0; i < M; i++) { // 각 상어의 방향에 따른 우선순위
			for(int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int d = 0; d < 4; d ++) {
					priority[i][j][d] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		int t = 0;
		while(t++ < 1000 ) { // 1000초가 될 때 까지 다른 상어 있으면 -1
			moveShark(); //상어 이동
			Collections.sort(sList);
			putSmell(); //냄새 뿌리기
			if(sList.size() == 1 && sList.get(0).num ==0) { // 남아있는 상어가 1번 상어일 때
				System.out.println(t);
				return;
			}
		}
		System.out.println(-1);
	}
	private static void moveShark() {
		ArrayList<Integer> clean = new ArrayList<>(); //인접방향 중 냄새 없는 방향
		ArrayList<Integer> mine = new ArrayList<>(); // 내냄새 방향
		for(Shark s : sList) {
			clean.clear();
			mine.clear();
			int r = s.r, c = s.c;
			int[] pr = priority[s.num][s.d];
			
			int nr,nc;
			for(int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(isIn(nr,nc)) {
					if(sm[nr][nc].sec == 0) { // 냄새 없으면
						clean.add(d);
					}else if(sm[nr][nc].num == s.num) { //내냄새면
						mine.add(d);
					}
				}
			}
			if(!clean.isEmpty()) {
				checkMap(s, clean, pr, r, c); //이동 맵에 체크
				continue;
			}else if(!mine.isEmpty()) {
				checkMap(s, mine, pr, r, c);
				continue;
			}
		}
		
	}
	private static void checkMap(Shark s, ArrayList<Integer> list, int[] pr, int r, int c) {
		for(int d : pr) {
			if(list.contains(d) && isIn(r + dr[d], c + dc[d])) { //우선순위에 따라서
				sh[r][c].remove(s); //현재 위치에서 없애주고
				s.r = r + dr[d]; //이동
				s.c = c + dc[d]; 
				s.d = d; //방향 변경
				sh[r + dr[d]][c + dc[d]].add(s); //이동 위치에 추가
				return;
			}
		}
		
	}
	private static void putSmell() { //냄새 관리
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int size = sh[i][j].size();
				if(size == 0 ) { // 현재 상어 없는칸
					if(sm[i][j].sec != 0) //남아있는 냄새 있을 경우
						--sm[i][j].sec; //1초 감소
				}
				else { // 상어 있는 칸
					if(size > 1) killShark(i, j); //상어 여러개 일 경우
					sm[i][j].num = sh[i][j].get(0).num; //냄새 표시
					sm[i][j].sec = K;
				}
			}
		}
	}
	
	private static void killShark(int r, int c) {
		Collections.sort(sh[r][c]); // 가장 작은 번호 상어만 살아남음
		for(int i = 1, end = sh[r][c].size(); i < end; i++) {
			sList.remove(sh[r][c].get(i)); //상어 리스트에서 지워주고
		}
		for(int i = 1, end = sh[r][c].size(); i < end; i++) {
			sh[r][c].remove(sh[r][c].size() - 1); // 맨 앞 상어 빼고 지워줌
		}
	}
	
	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	static class Shark implements Comparable<Shark>{
		int num, r, c, d;
		
		public Shark(int n, int r, int c) {
			super();
			this.num = n;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Shark o) {
			return this.num - o.num;
		} 
		
	}
	static class Smell{
		int num, sec;

		public Smell(int num, int sec) {
			super();
			this.num = num;
			this.sec = sec;
		}
		
	}
}
