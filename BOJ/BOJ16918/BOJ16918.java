package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ16918 {
	static int R, C, N;
	static LinkedList<Integer>[] bombList;
	static ArrayList<Integer> tmp;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];
		bombList = new LinkedList[R];

		for (int r = 0; r < R; r++) {
			bombList[r] = new LinkedList<Integer>();
		}
		String line;
		for (int r = 0; r < R; r++) {
			line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				if (map[r][c] == 'O') {
					bombList[r].add(c);
				}
			}
		}

		if (N % 2 == 0) {
			for (int r = 0; r < R; r++) {
				Arrays.fill(map[r], 'O');
			}
		} else if (N > 2) {
			bfs();
			if (N % 4 == 1) {
				findBomb();
			}
			for (int r = 0; r < R; r++) {
				Arrays.fill(map[r], 'O');
				for (int c : bombList[r]) {
					map[r][c] = '.';
				}
			}
		}

		for (int r = 0; r < R; r++) {
			bw.write(map[r]);
			bw.newLine();
		}
		bw.close();
		return;
	}

	private static void findBomb() {
		for (int r = 0; r < R; r++) {
			tmp = new ArrayList<Integer>();
			for (int c = 0; c < C; c++) {
				if (!bombList[r].contains(c)) {
					tmp.add(c);
				}
			}
			bombList[r].clear();
			bombList[r].addAll(tmp);
		}
		bfs();
	}

	private static void bfs() {
		ArrayList<Pair> b = new ArrayList<Pair>();
		boolean[][] visited = new boolean[R][C];
		int nr, nc;
		for (int r = 0; r < R; r++) {
			for (int c : bombList[r]) {
				visited[r][c] = true;
				for (int d = 0; d < 4; d++) {
					nr = r + dr[d];
					nc = c + dc[d];
					if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc]) {
						b.add(new Pair(nr, nc));
					}
				}
			}
		}
		for (Pair p : b) {
			bombList[p.r].add(p.c);
		}
	}

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}
}
