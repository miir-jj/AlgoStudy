package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 4014 활주로 건설
 */
public class SWEA4014 {
//	PriorityQueue<int[]> pq=new PriorityQueue<>(50,(x,y)->return x[1]-y[1]
	static int N, X, ans, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			bw.write("#" + t + " ");
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ans = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			row();
			column();
			bw.write(ans + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static void column() {
		int cur, len;
		boolean flag;
		for (int j = 0; j < N; j++) {
			cur = map[0][j];
			len = 1;
			flag = true;
			for (int i = 1; i < N; i++) {
				if (cur == map[i][j])
					++len;
				else if (cur - map[i][j] == 1) {
					cur = map[i][j];
					if (len < 0)
						break;
					len = -X + 1;
				} else if (cur - map[i][j] == -1) {
					if (len < X) {
						flag = false;
						break;
					}
					cur = map[i][j];
					len = 1;
				} else {
					flag = false;
					break;
				}
			}
			if (flag && len >= 0)
				++ans;
		}
	}

	private static void row() {
		int cur, len;
		boolean flag;
		for (int i = 0; i < N; i++) {
			cur = map[i][0];
			len = 1;
			flag = true;
			for (int j = 1; j < N; j++) {
				if (cur == map[i][j])
					++len;
				else if (cur - map[i][j] == 1) {
					cur = map[i][j];
					if (len < 0)
						break;
					len = -X + 1;
				} else if (cur - map[i][j] == -1) {
					if (len < X) {
						flag = false;
						break;
					}
					cur = map[i][j];
					len = 1;
				} else {
					flag = false;
					break;
				}
			}
			if (flag && len >= 0)
				++ans;
		}
	}
}