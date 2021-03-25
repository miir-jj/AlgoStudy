package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1227 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		boolean[][] map = new boolean[100][100];
		String s;
		int sr=0, sc=0, er=0, ec=0;
		for (int t = 1; t <= 10; t++) {
			bw.write("#"+t+" ");
			int ans=0;
			br.readLine();
			for (int i = 0; i < 100; i++) {
				s = br.readLine();
				for (int j = 0; j < 100; j++) {
					if (s.charAt(j) == '2') {
						sr=i;sc=j;
						map[i][j]=true;
					}else if(s.charAt(j)=='3') {
						er=i;ec=j;
						map[i][j]=true;
					}else if(s.charAt(j)=='0') {
						map[i][j]=true;
					}else {
						map[i][j]=false;
					}
				}
			}
			
			Queue<int[]> q=new LinkedList<int[]>();
			int[] cur;
			int[] dr= {-1,1,0,0};
			int[] dc= {0,0,-1,1};
			
			q.offer(new int[] {sr,sc});
			map[sr][sc]=false;
			int nr,nc;
			while(!q.isEmpty()) {
				cur=q.poll();
				if(cur[0]==er&&cur[1]==ec) {
					ans=1;
					break;
				}
				for(int d=0;d<4;d++) {
					nr=cur[0]+dr[d];
					nc=cur[1]+dc[d];
					if(nr>=0&&nr<100&&nc>=0&&nc<100&&map[nr][nc]) {
						q.offer(new int[] {nr,nc});
						map[nr][nc]=false;
					}
				}
			}
			bw.write(ans+"\n");
			bw.flush();
		}
		bw.close();
	}
	
	
	
	
	
	
	
	
}
