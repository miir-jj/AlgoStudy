package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1219 {
	public static void main(String[] args) throws Exception {
		int T=10;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=null;
		
		int E;
		for(int t=1;t<=T;t++) {
			bw.write("#"+t+" ");
			st=new StringTokenizer(br.readLine()," ");
			st.nextToken();
			E=Integer.parseInt(st.nextToken());
			LinkedList<Integer>[] graph=new LinkedList[100];
			
			for(int i=0;i<100;i++) {
				graph[i]=new LinkedList<>();
			}
			st=new StringTokenizer(br.readLine()," ");
			int from,to;
			for(int i=0;i<E;i++) {
				from=Integer.parseInt(st.nextToken());
				to=Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}
			Queue<Integer> q=new LinkedList<Integer>();
			boolean[] visited=new boolean[100]; // int형으로 선언하고, 방문 할 때마다 1더해주기
			q.add(0);
			visited[0]=true;
			
			int cur,ans=0;
			while(!q.isEmpty()) {
				cur=q.poll();
				if(cur==99) {
					ans=1;
					break;
				}
				visited[cur]=true;
				for(int i:graph[cur]) {
					if(!visited[i]) {
						q.offer(i);
						visited[i]=true;
					}
				}
			}
			bw.write(ans+"\n");
			bw.flush();
		}
		bw.close();
	}
}
