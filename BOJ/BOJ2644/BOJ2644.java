package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2644 {
	static int N,ans;
	static LinkedList<Integer>[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph=new LinkedList[N+1];
		ans=-1;
		for(int i=0;i<=N;i++) {
			graph[i]=new LinkedList<Integer>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		int a,b;
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
			
		}
		bfs(s,e);
		System.out.println(ans);
	}
	private static void bfs(int s, int e) {
		Queue<Integer> q=new LinkedList<Integer>();
		boolean[] visited=new boolean[N+1];
		q.offer(s);
		visited[s]=true;
		
		int cur,level=0;
		while(!q.isEmpty()) {
			int size=q.size();
			++level;
			for(int i=0;i<size;i++) {
				cur=q.poll();

				for(int k:graph[cur]) {
					if(k==e) {
						ans=level;
						return;
					}
					if(visited[k]) continue;
					q.offer(k);
					visited[k]=true;
				}
			}
		}
		
	}
	
	
	
	
	
	
	
}
