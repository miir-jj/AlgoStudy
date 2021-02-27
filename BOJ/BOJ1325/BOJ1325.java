package BOJ1325;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1325 {

	static int N, M, max;
	static boolean[] visited;
	static int[] result;
	static ArrayList<Integer>[] computers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		computers = new ArrayList[N];
		visited = new boolean[N ];
		result = new int[N ];

		for (int i = 0; i < N; i++) {
			computers[i]=new ArrayList<>();
		}

		int a, b;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			computers[a].add(b);
		}

		for (int i = 0; i < N ; i++) {
			bfs(i);
			Arrays.fill(visited, false);
		}
		
		for(int i=0;i<N;i++) {
			max=max<result[i]?result[i]:max;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N ; i++) {
			if (result[i] == max)
				sb.append(i+1).append(" ");
		}
		System.out.println(sb);
	}

	public static void bfs(int computer) {
		Queue<Integer> needVisit = new LinkedList<>();
		needVisit.offer(computer);
		visited[computer] = true;

		int c;
		while (!needVisit.isEmpty()) {
			c = needVisit.poll();
			for (int i = 0, end = computers[c].size(); i < end; i++) {
				int tmp = computers[c].get(i);
				if (!visited[tmp]) {
					visited[tmp] = true;
					needVisit.offer(tmp);
					++result[tmp];
				}
			}
		}
	}
}
