package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ17281 ⚾
 */
public class BOJ17281 {
	static int order[], result[][];
	static int N, max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		result = new int[N][9];
		order = new int[9];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combi(0, 0, 0);
		System.out.println(max);
	}

	private static void combi(int idx, int start, int flag) {
		if (idx == 9) {
			if (order[3] != 0)
				return;
			int score = game();
			max = max > score ? max : score;
			return;
		}
		for (int i = 0; i < 9; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			order[idx] = i;
			combi(idx + 1, start + 1, flag | 1 << i);
		}
	}

	private static int game() {
		boolean[] base = new boolean[3];
		int out = 0, inning = 0, now = 0, score = 0;

		while (inning < N) {
			switch (result[inning][order[now++%9]]) {
			case 0:
				++out;
				if (out == 3) {
					++inning;
					Arrays.fill(base, false);;
					out = 0;
					continue;
				}
				break;
			case 1:
				if (base[2])
					++score;
				base[2] = base[1];
				base[1] = base[0];
				base[0] = true;
				break;
			case 2:
				if (base[2] && base[1])
					score += 2;
				else if (base[2] || base[1])
					++score;
				base[2] = base[0];
				base[1] = true;
				base[0] = false;
				break;
			case 3:
				for (int j = 0; j < 3; j++) {
					if (base[j]) {
						++score;
						base[j] = false;
					}
				}
				base[2] = true;
				break;
			case 4:
				for (int j = 0; j < 3; j++) {
					if (base[j]) {
						++score;
						base[j] = false;
					}
				}
				++score;
				break;
			}
		}
		return score;
	}

}
