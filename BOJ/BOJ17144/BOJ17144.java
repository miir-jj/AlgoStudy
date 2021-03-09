package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/** 미세먼지 안녕! 
 * https://www.acmicpc.net/problem/17144
 */
public class BOJ17144 {
	static int R,C,T;
	static int[][] map,update;
	static int[] cleaner;
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		int cnt=0,dust=0;
		int[] dir1= {3,0,2,1};
		int[] dir2= {3,1,2,0};
		
		map=new int[R][C];
		update=new int[R][C];
		cleaner=new int[2];
		
		for(int r=0;r<R;r++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int c=0;c<C;c++) {
				map[r][c]=Integer.parseInt(st.nextToken());
				if(map[r][c]==-1) {
					cleaner[cnt++]=r;
				}
			}
		}
		for(int t=0;t<T;t++) {
			for(int r=0;r<R;r++) {
				Arrays.fill(update[r], 0);
			}
			for(int r=0;r<R;r++) {
				for(int c=0;c<C;c++) {
					if(map[r][c]>0) {
						spread(r,c);
					}
				}
			}
			for(int r=0;r<R;r++) {
				System.arraycopy(update[r], 0, map[r], 0, C);
			}
			clear(cleaner[0],0,dir1);
			clear(cleaner[1],0,dir2);
			map[cleaner[0]][0]=-1;
			map[cleaner[1]][0]=-1;
		}
		
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(map[r][c]>0) {
					dust+=map[r][c];
				}
			}
		}
		System.out.println(dust);
	}
	private static void spread(int r,int c) {
		int nr,nc;
		int dust=map[r][c]/5;
		
		for(int d=0;d<4;d++) {
			nr=r+dr[d];
			nc=c+dc[d];
			if(nr>=0&&nr<R&&nc>=0&&nc<C&&map[nr][nc]!=-1) {
				update[nr][nc]+=dust;
				map[r][c]-=dust;
			}
		}
		update[r][c]+=map[r][c];
	}
	private static void clear(int r,int c, int[] dir) {
		int nr,nc;
		for(int d=0;d<4;d++) {
			while(true) {
				nr=r+dr[dir[d]];
				nc=c+dc[dir[d]];
				
				if((nr==cleaner[0]||nr==cleaner[1])&&nc==0)
					break;
				if(nr<0||nr>R-1||nc<0||nc>C-1)
					break;
				map[nr][nc]=update[r][c];
				r=nr;
				c=nc;
			}
		}
	}
}
