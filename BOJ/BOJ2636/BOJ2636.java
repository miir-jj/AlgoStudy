package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
	static int N, M, cnt;
	static int[][] map;
	static boolean visited[][];
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					++cnt;
			}
		}
		boolean flag;
		int nr,nc;
		int sec=0,cheese=cnt;
		while(cnt>0) {
			cheese=cnt;
			findHole();
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					flag=true;
					if(map[i][j]==1) {
						for(int d=0;d<4;d++) {
							nr=i+dr[d];
							nc=j+dc[d];
							if(nr>=0&&nc>=0&&nr<N&&nc<M&&map[nr][nc]==-1) {
								flag=false;
								break;
							}
						}
					}
					if(!flag) {
						--cnt;
						map[i][j]=2;
					}
				}
			}
			++sec;
		}
		System.out.println(sec+"\n"+cheese);
	}

	private static void findHole() {
		visited=new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visited[i][j]&&map[i][j]!=1) {
					bfs(i,j);
				}
			}
		}
	}
	private static void bfs(int r,int c) {
		Queue<Pair> q=new LinkedList<Pair>();
		ArrayList<Pair> pList=new ArrayList<Pair>();
		int flag=-2;
		q.offer(new Pair(r,c));
		visited[r][c]=true;
		
		Pair cur;
		int nr,nc;
		while(!q.isEmpty()) {
			cur=q.poll();
			if(cur.r==0||cur.r==N-1||cur.c==0||cur.c==M-1) flag=-1;
			pList.add(cur);
			for(int d=0;d<4;d++) {
				nr=cur.r+dr[d];
				nc=cur.c+dc[d];
				if(nr>=0&&nr<N&&nc>=0&&nc<M&&!visited[nr][nc]&&map[nr][nc]!=1) {
					q.offer(new Pair(nr,nc));
					visited[nr][nc]=true;
				}
			}
		}
		for(Pair p:pList) {
			map[p.r][p.c]=flag;
		}
	}
	static class Pair{
		int r,c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
