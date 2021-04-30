package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * BOJ9372 상근이의 여행
 * N - 1이 출제 의도였단다
 * 충격과 공포
 * 최소신장트리 쓰려다가 이상해서 찾아봄
 * @author 미령
 *
 */

public class BOJ9372 {
	static int N, M;
	static LinkedList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		for (int t = 1, end = Integer.parseInt(br.readLine()); t <= end; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				br.readLine();
			}
			bw.write(N - 1 + "\n");
			bw.flush();
		}
		bw.close();
	}
}
