package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 2251 물통
 * 
 * @author 미령
 * 각 물통이 가질 수 있는 무게만큼 visited 배열 생성
 * bfs를 돌며 나온 현재 물통 상황에서
 * 첫번째 물통이 두번째 물통, 세번째 물통으로 옮겼을 때 각각 가지고 있는 물의 양을 계산해 visit체크 한 후 bfs 돈다
 * 두번째 세번째 물통도 마찬가지
 * 옮길 물의 양과 이동시킬 물통에 남아있는 공간 중 더 작은 값을 물이 있던 물통에서 빼고 이동시킬 물통에 더해준다. 
 */
public class BOJ2251 {
	static int[] max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		max = new int[3];
		for (int i = 0; i < 3; i++) {
			max[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][][] visited = new boolean[max[0] + 1][max[1] + 1][max[2] + 1];
		Set<Integer> c = new HashSet<Integer>();
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] { 0, 0, max[2] });
		visited[0][0][max[2]] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == 0) {
				c.add(cur[2]);
			}
			for (int i = 0; i < 3; i++) {
				if (cur[i] == 0)
					continue; // 옮길 물이 없는 경우
				for (int j = 0; j < 3; j++) {
					if (i == j)  continue;
					
					int[] temp = Arrays.copyOf(cur, 3);
					int possible = temp[i] < max[j] - temp[j] ? temp[i] : max[j] - temp[j];

					if (possible == 0) 	continue;
					temp[j] += possible;
					temp[i] -= possible;

					if (!visited[temp[0]][temp[1]][temp[2]]) {
						visited[temp[0]][temp[1]][temp[2]] = true;
						q.offer(new int[] {temp[0], temp[1], temp[2]});
					}
				}
			}
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(c);
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			sb.append(pq.poll() + " ");
		}
		System.out.println(sb.toString());
	}
}
