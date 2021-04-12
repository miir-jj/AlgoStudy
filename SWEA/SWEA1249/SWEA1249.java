package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * SWEA1249 보급로
 */
public class SWEA1249 {
	static int[][] map;
	static int N;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			bw.write("#"+t+" ");
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				String str=br.readLine().trim();
				for(int j=0;j<N;j++) {
					map[i][j]=str.charAt(j)-'0';
				}
			}
			bw.write(bfs()+"\n");
			bw.flush();
		}
		bw.close();
	}
	private static int bfs() {
		Queue<Pair> q=new LinkedList<Pair>();
		int[][] recover=new int[N][N];
		for(int i=0;i<N;i++) {
			Arrays.fill(recover[i], Integer.MAX_VALUE);
		}
		q.offer(new Pair(0,0));
		recover[0][0]=0;
		
		Pair p;
		int nr,nc;
		while(!q.isEmpty()) {
			p=q.poll();
			
			for(int d=0;d<4;d++) {
				nr=p.r+dr[d];
				nc=p.c+dc[d];
				if(nr>=0&&nr<N&&nc>=0&&nc<N&&recover[nr][nc]>map[nr][nc]+recover[p.r][p.c]) {
					recover[nr][nc]=map[nr][nc]+recover[p.r][p.c];
					q.offer(new Pair(nr,nc));
				}
			}
		}
		return recover[N-1][N-1];
	}
	static class Pair{
		int r,c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
}
