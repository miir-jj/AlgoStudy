package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 20165 인내의 도미노 장인 호석
 * 방향이 문자로 들어오므로 해시맵으로 숫자 맵핑 ( N : 0, S : 1, W : 2, E : 3)
 * domino객체 배열로 각 좌표의 도미노 높이와 쓰러졌는지 여부를 저장
 * 시작 도미노의 길이를 len으로 두고, 0보다 클 동안 while문을 돈다
 * 다음 도미노가 쓰러지지 않았고, 현재의 len보다 높이가 크면 len을 갱신
 * 
 * @author 미령
 *
 */
public class BOJ20165 {
	static int N, M, R, score;
	static Domino[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new Domino[N][M];
		HashMap<String, Integer> dir = new HashMap<String, Integer>();
		dir.put("N", 0);
		dir.put("S", 1);
		dir.put("W", 2);
		dir.put("E", 3);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = new Domino(Integer.parseInt(st.nextToken()));
			}
		}
		while (R-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			attack(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, dir.get(st.nextToken()));
			st = new StringTokenizer(br.readLine(), " ");
			defense(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		bw.write(score + "\n");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j].flag) bw.write("F ");
				else bw.write("S ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	private static void defense(int r, int c) {
		if(map[r][c].flag)
			map[r][c].flag = false;
	}

	private static void attack(int r, int c, int d) {
		int nr = r, nc = c;
		int len = map[nr][nc].len;
		while(len-- > 0) {
			if(!map[nr][nc].flag) {
				++score;
				map[nr][nc].flag = true;
			}
			nr+=dr[d];
			nc+=dc[d];
			if(len == 0 || nr <0 || nr >= N || nc < 0 || nc >= M) return;
			if(!map[nr][nc].flag && len < map[nr][nc].len) len = map[nr][nc].len;
			
		}
		
	}

	static class Domino {
		int len;
		boolean flag;

		public Domino(int len) {
			this.len = len;
			this.flag = false;
		}

	}
}
