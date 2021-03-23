package jo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JO1411 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] tiles = new int[N + 1];
		tiles[1] = 1;
		tiles[2] = 3;

		for (int i = 3; i <= N; i++) {
			tiles[i] = (tiles[i - 1] + tiles[i - 2] * 2)%20100529;
		}
		System.out.println(tiles[N]);
	}
}
