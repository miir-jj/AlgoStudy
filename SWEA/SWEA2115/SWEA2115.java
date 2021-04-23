package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2115 벌꿀채취
 * @author 미령
 * 부분집합 + 조합
 * 
 * 행 별로 M개의열에서 꿀을 채취했을 때 얻을 수 있는 최대 이익 
 * 	-> 부분집합으로 구해서 배열에 저장해둠
 * 조합을 통해 두개를 뽑아 최대 이익을 찾는다.
 */
public class SWEA2115 {
	static ArrayList<Integer> con;
	static int N, M, C, maxSum, map[][], profit[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		for (int t = 1, end = Integer.parseInt(br.readLine()); t <= end; t++) {
			bw.write("#" + t + " ");
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			profit = new int[N][N - M + 1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bw.write(getMaxProfit() + "\n");
			bw.flush();
		}
		bw.close();
	}

	private static int getMaxProfit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				maxSum = 0;
				makeMaxSubset(i,j);
			}
		}
		return makeCombi();
	}

	private static int makeCombi() {
		int result = 0, aProfit = 0, bProfit = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) { // 첫 열부터 연속된 M개 채취가 가능한 열까지, 일꾼 A의 선택
				// 선택된 M개 벌통에서 얻을 수 있는 최대 이익
				aProfit = profit[i][j];

				// 일꾼 B의 선택
				// 일꾼 A와 같은 행의 뒤쪽 열에서 선택
				bProfit = 0;
				for (int j2 = j + M; j2 <= N - M; j2++) { // 일꾼 A가 선택한 바로 뒤열부터 M개 선택가능한 열까지
					bProfit = profit[i][j2];
				}
				// 일꾼 A의 다음행 부터 선택
				for (int i2 = i + 1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N - M; j2++) {
						bProfit = bProfit > profit[i2][j2] ? bProfit : profit[i2][j2];
					}
				}
				result = result > aProfit + bProfit ? result : aProfit + bProfit;
			}
		}
		return result;
	}

	private static void makeMaxSubset(int r, int c) { // M만큼의 열에서 C를 안넘고 얻을 수 있는 최대 이익 설정
		for (int i = 1, end = 1 << M; i < end; i++) {
			int sum = 0, pSum = 0;
			boolean flag = true;
			for (int j = 0; j < M; j++) {
				if ((i & 1 << j) != 0) {
					sum += map[r][c + j];
					pSum += map[r][c + j] * map[r][c + j];
					if (sum > C) { //C를 넘었을 때
						flag = false;
						break;
					}
				}
			}
			if(flag)  // C 안넘었으면
				maxSum = maxSum > pSum ? maxSum : pSum;
		}
		profit[r][c] = maxSum;
	}
}
