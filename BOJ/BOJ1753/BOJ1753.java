package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최단경로
 */
public class BOJ1753 {
	static class Vertex implements Comparable<Vertex> {
		int v, w;

		public Vertex(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(br.readLine());

		LinkedList<Vertex>[] adjList = new LinkedList[V+1];
		int[] dist = new int[V + 1];
		boolean[] visited = new boolean[V + 1];

		for (int i = 0; i <= V; i++) {
			adjList[i]=new LinkedList<Vertex>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			adjList[Integer.parseInt(st.nextToken())]
					.add(new Vertex(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		PriorityQueue<Vertex> minHeap=new PriorityQueue<>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[S] = 0;

		minHeap.offer(new Vertex(S,dist[S]));
		int cur = 0, min = 0;
		Vertex now;
		while(!minHeap.isEmpty()) {
			now=minHeap.poll();
			cur=now.v;
			min=now.w;
			
			if(min>dist[cur]) continue;
			visited[cur] = true;
			
			for (Vertex v : adjList[cur]) {
				if (!visited[v.v] && dist[v.v] > min + v.w) {
					dist[v.v] = min + v.w;
					minHeap.offer(new Vertex(v.v,dist[v.v]));
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				bw.write("INF\n");
			} else {
				bw.write(dist[i] + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
