package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 5643 키 순서
 * 나보다 작은사람, 큰사람 그래프를 두개 만들어 bfs를 두번 돈다
 * bfs를 모두 돈 후, 방문한 노드 수가 N-1이면 모든 노드와 비교한 것 -> 순위 알 수 있음
 * @author 미령
 *
 */
public class SWEA5643_sol {
	static int N, M, big[], small[];
	static boolean[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		for(int t = 1, end = Integer.parseInt(br.readLine().trim()); t <= end; t++) {
			bw.write("#" + t + " ");
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			map = new boolean[N][N];
			big = new int[N];
			small = new int[N];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
			}
			Arrays.fill(big, -1);
			for(int i = 0; i < N; i++) {
				if(big[i] == -1) dfs(i);
			}
			
			for(int j= 0; j < N; j++) {
				for(int i = 0; i < N; i++) {
					if(map[i][j]) ++small[j];
				}
			}
			
			int ans = 0;
			for(int i = 0; i < N; i++) {
				if(big[i] + small[i] == N - 1) ++ans;
			}
			bw.write(ans + "\n");
			bw.flush();
		}
		bw.close();
	}
	private static void dfs(int cur) {
		for(int i = 0; i < N; i++) {
			if(map[cur][i]) {
				if(big[i] == -1) dfs(i); //아직 탐색하지 않은 학생이면 탐색
				
				//i 학생을 탐색하고 왔거나, 이미 탐색되어 있을 때
				if(big[i] > 0) { //i보다 큰 학생이 있다면 -> cur에 표시
					for(int j = 0; j < N; j++) {
						if(map[i][j]) map[cur][j] = true;
					}
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(map[cur][i]) ++cnt;
		}
		big[cur] = cnt;
	}
}
