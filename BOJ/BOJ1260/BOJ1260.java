package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {
	static int N, M, V;
	static LinkedList<Integer>[] map;
	static boolean[] dVisit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		dVisit = new boolean[N + 1];

		map = new LinkedList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			map[i] = new LinkedList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from].add(to);
			map[to].add(from);
		}
		for (int i = 0; i < N + 1; i++) {
			Collections.sort(map[i]);
			;
		}
		dfs(V);
		System.out.println();
		bfs(V);
	}

	private static void bfs(int cur) {
		StringBuilder sb = new StringBuilder();
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(cur);
		visited[cur] = true;

		while (!q.isEmpty()) {
			cur = q.poll();
			for (int ad : map[cur]) {
				if (!visited[ad]) {
					q.offer(ad);
					visited[ad] = true;
				}
			}
			sb.append(cur + " ");
		}
		System.out.println(sb.toString());
	}


	private static void dfs(int cur) {
		dVisit[cur] = true;
		System.out.print(cur + " ");
		for (int ad : map[cur]) {
			if (!dVisit[ad])
				dfs(ad);
		}

	}
}
