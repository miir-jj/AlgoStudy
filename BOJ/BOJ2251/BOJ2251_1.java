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
 * visit체크 set으로 한 버전
 * @author 미령
 *
 */
public class BOJ2251_1 {
	static int[] max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		max = new int[3];
		for (int i = 0; i < 3; i++) {
			max[i] = Integer.parseInt(st.nextToken());
		}

		Set<Order> visited = new HashSet<Order>();
		Set<Integer> c = new HashSet<Integer>();
		Queue<Order> q = new LinkedList<Order>();
		Order first = new Order(new int[] { 0, 0, max[2] });
		q.offer(first);
		visited.add(first);
		while (!q.isEmpty()) {
			Order cur = q.poll();
			if (cur.now[0] == 0) {
				c.add(cur.now[2]);
			}
			for (int i = 0; i < 3; i++) {
				if (cur.now[i] == 0)
					continue; // 옮길 물이 없는 경우
				for (int j = 0; j < 3; j++) {
					if (i == j)  continue;
					
					int[] temp = Arrays.copyOf(cur.now, 3);
					int possible = temp[i] < max[j] - temp[j] ? temp[i] : max[j] - temp[j];

					if (possible == 0) 	continue;
					temp[j] += possible;
					temp[i] -= possible;

					Order o = new Order(temp);
					if (!visited.contains(o)) {
						visited.add(o);
						q.offer(o);
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

	static class Order {
		int[] now;

		public Order(int[] now) {
			this.now = now;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(now);
		}

		@Override
		public boolean equals(Object obj) {
			return this.hashCode() == obj.hashCode();
		}
	}

}
