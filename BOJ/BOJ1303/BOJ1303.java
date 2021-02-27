package boj_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 전투 */
public class BOJ1303 {

	static char[][] map;
	static int N, M;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[M][N];

		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		System.out.println(findTeam('W')+" "+findTeam('B'));
	}

	public static int findTeam(char c) {
		int num = 0;
		int sum = 0;
		Queue<int[]> team = new LinkedList<int[]>();
		boolean[][] isVisited = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) { //전체 맵을 돌며
				if (map[i][j] == c &&!isVisited[i][j]) { //해당 팀이면서 아직 방문 전인 노드일 경우
					team.offer(new int[] { i, j }); 
					isVisited[i][j] = true; 
					++num; 
					int[] soldier=new int[2];
					int nr,nc;
					while(!team.isEmpty()) { //큐가 빌때까지 사방 탐색
						soldier=team.poll();
						for(int k=0;k<dr.length;k++) {
							nr=soldier[0]+dr[k];
							nc=soldier[1]+dc[k];
							
							if(nr>-1&&nr<M&&nc>-1&&nc<N&&map[nr][nc]==c&&!isVisited[nr][nc]) {
								team.offer(new int[] {nr,nc});
								isVisited[nr][nc]=true;
								++num;
								
							}
						}
					}
				}
				sum+=num*num; //한번 큐가 비면->주위에 같은 팀 없다는 뜻으로 제곱해서 더해주고 다음 좌표로 이동
				num=0;
			}
		}
		return sum;
	}

}