package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 양팔저울 https://www.acmicpc.net/problem/2629
 */
public class BOJ2629 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<Integer> sinker = new HashSet<>();
		HashSet<Integer> tmp = new HashSet<>();

		int s = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < s; i++) {
			dp(Integer.parseInt(st.nextToken()), sinker, tmp);
		}

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int a = 0;
		for (int i = 0; i < m; i++) {
			a = Integer.parseInt(st.nextToken());
			if (sinker.contains(a)) {
				sb.append("Y").append(" ");
			} else {
				sb.append("N").append(" ");
			}
		}
		System.out.println(sb.toString());
	}

	public static void dp(int w, HashSet<Integer> sinker, HashSet<Integer> tmp) {
		Iterator<Integer> ir = sinker.iterator();
		while (ir.hasNext()) {
			int n = ir.next();
			tmp.add(n + w);
			tmp.add(Math.abs(n - w));
		}
		tmp.add(w);
		sinker.addAll(tmp);
		tmp.clear();
	}

}
