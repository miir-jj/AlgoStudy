package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/** 수들의 합 */
public class BOJ2003 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M, cnt = 0, sum = 0;

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] num = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0, end = 0;
		while (end < N) { //마지막 원소까지 더했을 때 N일 경우까지
			if(num[end]==M) {
				++cnt;
				sum=0;
				start=++end;
				continue;
			}
			
			if (sum < M) {
				sum += num[end++];
			} else if(sum>M){
				sum-=num[start++];
			}
			if (sum == M) {
				++cnt;
				sum-=num[start++];
			}
		}
		
		System.out.println(cnt);
	}
}
