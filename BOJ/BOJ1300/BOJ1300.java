package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**  수들의 합2  */
public class BOJ1300 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		long low = 1, high = K, ans = 0, mid = 0, cnt = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			cnt = 0;
			for (int i = 1; i <= N; i++) {
				cnt += mid / i < N ? mid / i : N;
			}

			if (cnt < K)
				low = mid + 1;
			else {
				ans=mid; //ans값 왜 저장하는지 이해 가지 않았었음 -> cnt가 이후 mid에서 K보다 작아질 수 있으므로 저장해두어야함
				high = mid - 1;
			}
		}

		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}
}
