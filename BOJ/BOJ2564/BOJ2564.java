package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * 2564 경비원
 */
public class BOJ2564 {
	static int R, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		int[] dir = { 0, 2, 1, 4, 3 };

		ArrayList<int[]>[] store = new ArrayList[5];
		for (int i = 0; i < 5; i++) {
			store[i] = new ArrayList<int[]>();
		}
		//방향별 상점 위치 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			store[a].add(getLocation(a, b));
		}
		//동근이 위치 저장
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[] dong = getLocation(a, b);
		int ans = 0;
		//수평위치 상점은 getDistance, 아니면 좌표차 구하기
		for (int i = 1; i <= 4; i++) {
			for (int[] s : store[i]) {
				if (i == dir[a]) {
					ans += getDistance(dir[a], s, dong) * 2;
				}
				ans += Math.abs(dong[0] - s[0]) + Math.abs(dong[1] - s[1]);
			}
		}
		System.out.println(ans);
	}
	//양 끝단에서의 최솟값 구하기
	private static int getDistance(int dir, int[] s, int[] dong) {
		int min;
		if (dir == 1 || dir == 2) {
			min = dong[1] < s[1] ? dong[1] : s[1];
			min = min < C - dong[1] ? min : C - dong[1];
			min = min < C - s[1] ? min : C - s[1];
		} else {
			min = dong[0] < s[0] ? dong[0] : s[0];
			min = min < R - dong[0] ? min : R - dong[0];
			min = min < R - s[0] ? min : R - s[0];
		}

		return min;
	}

	public static int[] getLocation(int a, int b) {
		switch (a) {
		case 1:
			return new int[] { 0, b };
		case 2:
			return new int[] { R, b };
		case 3:
			return new int[] { b, 0 };
		default:
			return new int[] { b, C };
		}
	}
}
