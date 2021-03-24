package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11727 {
	/**
	 * 11727 2*n 타일링
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] tiles = new int[N + 1];
		tiles[0] = 1;
		tiles[1] = 1;

		for (int i = 2; i <= N; i++) {
//			tiles[i] = (tiles[i - 1] + tiles[i - 2] * 2)%20100529;
			tiles[i] = (tiles[i - 1] + tiles[i - 2] * 2)%10007;
		}
		System.out.println(tiles[N]);
	}
}
