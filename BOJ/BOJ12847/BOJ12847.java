package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 12847 꿀 아르바이트
 */
public class BOJ12847 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<Integer>();
		st = new StringTokenizer(br.readLine(), " ");
		long sum = 0, max= 0;
		int cur = 0;
		for (int i = 0; i < n; i++) {
			cur = Integer.parseInt(st.nextToken());
			q.offer(cur);
			sum += cur;
			if(q.size()>m) {
				sum = sum - q.poll();
			}
			max = max > sum ? max : sum;
		}
		System.out.println(max);
	}
}
