package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 7507 올림픽게임
 */
public class BOJ7507 {
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		String out = "Scenario #";
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			bw.write(out + t + ":\n");
			ans = 1;
			int N = Integer.parseInt(br.readLine());
			ArrayList<Game> gList = new ArrayList<Game>(N);

			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				gList.add(new Game(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())));
			}
			Collections.sort(gList);
			watch(gList);
			bw.write(ans + "\n");
			bw.newLine();
			bw.flush();
		}
		bw.close();
	}

	private static void watch(ArrayList<Game> gList) {
		Game g = gList.get(0);
		int e = g.e;
		int d = g.d;
		for (int i = 1, end = gList.size(); i < end; i++) {
			g = gList.get(i);
			if (g.s >= e || g.d > d) {
				++ans;
				e = g.e;
				d = g.d;
			} else if (g.e <= e) {
				e = g.e;
			}
		}
	}

	static class Game implements Comparable<Game> {
		int d, s, e;

		public Game(int d, int s, int e) {
			super();
			this.d = d;
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Game o) {
			if (this.d == o.d) {
				if (this.s == o.s) {
					return this.e - o.e;
				}
				return this.s - o.s;
			}
			return this.d - o.d;
		}

	}
}
/*
 * 2 10 1 1220 1340 2 1155 1220 2 1220 1340 3 1220 1240 1 1200 1320 2 1250 1310
 * 2 1330 1550 3 1030 1130 3 1130 1300 3 1240 1330 3 1 0500 2200 1 0000 0700 1
 * 2000 2359
 */
