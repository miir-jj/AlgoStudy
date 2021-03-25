package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3307 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		int N = 0;
		int[] A, LIS;
		for (int t = 1; t <= T; t++) {
			bw.write("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			A = new int[N];
			LIS = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int size = 0, idx = 0;
			LIS[size++] = A[0];
			for (int i = 1; i < N; i++) {
				// 0에서 size 인덱스 까지 LIS 배열 안에서 A[i]의 위치를 찾음 => 있을 경우 위치 반환
				idx = Math.abs(Arrays.binarySearch(LIS, 0, size, A[i])) - 1;
				LIS[idx] = A[i];

				if (idx == size)
					++size;
			}

			bw.write((size) + "\n");
			bw.flush();
		}
		bw.close();
	}
}
