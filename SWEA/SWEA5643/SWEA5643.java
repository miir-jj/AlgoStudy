package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 5643 키 순서
 * 나보다 작은사람, 큰사람 그래프를 두개 만들어 bfs를 두번 돈다
 * bfs를 모두 돈 후, 방문한 노드 수가 N-1이면 모든 노드와 비교한 것 -> 순위 알 수 있음
 * @author 미령
 *
 */
public class SWEA5643 {
	static int N, M;
	static ArrayList<Integer> small[], big[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		for(int t = 1, end = Integer.parseInt(br.readLine().trim()); t <= end; t++) {
			bw.write("#" + t + " ");
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			small = new ArrayList[N + 1]; // 나보다 작은사람
			big = new ArrayList[N + 1]; //나보다 큰사람
			for(int i = 0; i <= N; i++) {
				small[i] = new ArrayList<Integer>();
				big[i] = new ArrayList<Integer>();
			}
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				big[a].add(b);
				small[b].add(a);
			}
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
					if(bfs(i) == N - 1)
						++cnt;
			}
			bw.write(cnt + "\n");
			bw.flush();
		}
		bw.close();
	}
	private static int bfs(int node) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];
		q.offer(node);
		int cnt = 0;
		visited[node] = true; // 방문처리
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int n : small[cur]) { // 나보다 작은 사람
				if(visited[n]) continue;
				++cnt;
				visited[n] = true;
				q.offer(n);
			}
		}
		q.offer(node);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int n : big[cur]) { // 나보다 큰 사람
				if(visited[n]) continue;
				++cnt;
				visited[n] = true;
				q.offer(n);
			}
		}
		return cnt;
	}
}
