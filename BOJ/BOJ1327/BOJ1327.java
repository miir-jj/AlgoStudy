package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1327 소트게임
 * @author 미령
 *
 */
public class BOJ1327 {
	static int N, K, min = Integer.MAX_VALUE;
	static String ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			sb.append(i + 1);
		}
		ans = sb.toString();
		sb.delete(0, N);

		for (int i = 0; i < N; i++) {
			sb.append(st.nextToken());
		}

		bfs(sb.toString());

		if (min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);
	}

	private static void bfs(String s) {
		Queue<int[]> q = new LinkedList<int[]>();
		HashSet<String> visit = new HashSet<>();

		q.offer(new int[] { Integer.parseInt(s), 0 });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			String input = Integer.toString(cur[0]);
			if (input.equals(ans)) {
				min = cur[1];
				return;
			}
			for (int i = 0; i <= N - K; i++) {
				String copy = input.substring(i, i + K);
				StringBuilder temp = new StringBuilder(copy);

				temp.reverse();
				copy = input.replace(copy, temp);

				if (!visit.contains(copy)) {
					visit.add(copy);
					q.offer(new int[] { Integer.parseInt(copy), cur[1] + 1 });
				}
			}

		}

	}

}
