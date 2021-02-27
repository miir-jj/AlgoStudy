package BOJ2615;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 오목
 * https://www.acmicpc.net/problem/2615
 */
public class BOJ2615 {
	static String[][] map;
	static int[] dr = { -1, 0, 1, 1 };
	static int[] dc = { 1, 1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new String[19][];

		for (int i = 0; i < 19; i++) {
			map[i] = br.readLine().trim().split(" ");
		}
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (map[i][j].equals("0"))
					continue;
				for (int d = 0; d < 4; d++) {
					if (!game(i, j, d, 1))
						continue;

					int nr = i - dr[d];
					int nc = j - dc[d];
					if (nr > -1 && nr < 19 && nc > -1 && nc < 19 && map[i][j].equals(map[nr][nc]))//육목확인
						continue;
					System.out.println(map[i][j]);
					System.out.println((i + 1) + " " + (j + 1));
					return;
				}
			}
		}
		System.out.println("0");
	}

	public static boolean game(int r, int c, int dir, int cnt) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if (cnt == 5) {
			if(nr > -1 && nr < 19 && nc > -1 && nc < 19 && map[r][c].equals(map[nr][nc]))//육목확인
				return false;
			return true;
		}

		if (nr < 0 || nr >= 19 || nc < 0 || nc >= 19 || !map[nr][nc].equals(map[r][c]))
			return false;
		if (game(nr, nc, dir, cnt + 1))
			return true;

		return false;
	}
}
