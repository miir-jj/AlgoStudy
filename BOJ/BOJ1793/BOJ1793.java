package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
/** 타일링
 *  https://www.acmicpc.net/problem/1793
 */
public class BOJ1793 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = "";
		BigInteger[] dp = new BigInteger[251];
		int n = 0;
		dp[0] = BigInteger.ONE;
		dp[1] = BigInteger.ONE;
		BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

		for (int i = 2; i <= 250; i++) {
			dp[i] = dp[i - 1].add(dp[i - 2].multiply(TWO));
		}

		try {
			while ((line = br.readLine()) != "") {
				n = Integer.parseInt(line);
				bw.write(dp[n] + "\n");
				bw.flush();
			}
		} catch (NumberFormatException | IOException e) {
		}
	}

}
