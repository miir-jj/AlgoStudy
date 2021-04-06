package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 결혼식 5567
 * 양방향 체크 안해줘서 한번 틀림
 * 큐의 크기만큼씩 돈다
 */
public class BOJ5567 {
	static int N;
	static ArrayList<Integer>[] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		graph=new ArrayList[N+1];
		for(int i=0;i<=N;i++) {
			graph[i]=new ArrayList<Integer>();
		}
		StringTokenizer st;
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			graph[a].add(b);
			graph[b].add(a);
		}
		
		System.out.println(bfs());
	}
	private static int bfs() {
		Queue<Integer> q=new LinkedList<Integer>();
		boolean[] visited=new boolean[N];
		visited[0]=true;
		q.offer(0);
		int cnt=0,cur;
		
		for(int t=0;t<2;t++) {
			int size=q.size();
			while(size-->0) {
				cur=q.poll();
				for(int i:graph[cur]) {
					if(!visited[i]) {
						visited[i]=true;
						q.add(i);
						++cnt;
					}
				}
			}
		}
		return cnt;
	}
}
