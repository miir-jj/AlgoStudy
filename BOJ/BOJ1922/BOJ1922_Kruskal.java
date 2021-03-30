package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922_Kruskal {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> pq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		parent=new int[N+1];
		pq=new PriorityQueue<Edge>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		makeSet();
		Edge cur;
		int weight=0;
		while(!pq.isEmpty()) {
			cur=pq.poll();
			if(union(cur.from,cur.to)) {
				weight+=cur.w;
			}
		}
		System.out.println(weight);
	}
	private static void makeSet() {
		for(int i=1;i<=N;i++) {
			parent[i]=i;
		}
	}
	private static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a]=find(parent[a]);
	}
	private static boolean union(int a, int b) {
		int aRoot=find(a);
		int bRoot=find(b);
		
		if(aRoot==bRoot) return false;
		parent[aRoot]=bRoot;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int from,to,w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
		
	}
}
