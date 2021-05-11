package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 15486 퇴사2
 * 전날까지의 수입이 현재 날짜 수입보다 많으면 전날까지의 수입으로 현재수입 초기화
 * 현재 날짜에서 걸리는 상담 시간을 더했을 때 N을 넘지 않고, 
 * 해당 날짜의 수입이 현재 날짜까지의 수입 + 진행할 상담의 수입 보다 작다면
 * 해당 날짜의 수입을 현재까지 수입 + 진행할 상담 수입 으로 초기화
 * 
 * @author 미령
 *
 */
public class BOJ15486 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		int t, p;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			t = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			if (i == 0) {
				if (t <= N)
					dp[t] = dp[0] + p;
			} else {
				if (dp[i - 1] > dp[i])
					dp[i] = dp[i - 1];
				int target = i + t;
				if (target <= N && dp[target] < dp[i] + p) {
					dp[target] = dp[i] + p;
				}
			}
		}
		if (dp[N - 1] > dp[N])
			dp[N] = dp[N - 1];
		System.out.println(dp[N]);
	}

}
