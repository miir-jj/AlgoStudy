package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 20055 컨베이어 벨트 위의 로봇
 * 비교적 간단한 문제
 * 모든 스텝을 거친게 1단계를 수행한것!
 * -> 벨트가 돌아갈 때 로봇도 돌아가지만 내구도는 감소시키면 안됨 -> check flag로 처리
 * 벨트 회전은 재귀함수 이용
 * 로봇 회전은 가장 먼저 들어온 로봇부터 수행해야하기 때문에 queue이용
 * 
 * @author 미령
 *
 */
public class BOJ20055 {
	static int N, K, A[];
	static boolean robot[];
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N * 2];
		robot = new boolean[N]; // 해당 위치에 로봇 있는지 확인, 0에서 올라오고 N에서 내려가므로 이후 공간은 필요 없음
		q = new LinkedList<Integer>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0, end = A.length; i < end; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int step = 0;
		while (true) {
			++step;
			moveBelt(0, A[A.length - 1]); //벨트 & 로봇 이동(내구도 체크 x)
			moveRobot(true); // 로봇 이동(내구도 체크)
			onRobot(); // 로봇 올리기
			if(count()) { // 내구도 0인 부분 세기
				System.out.println(step);
				return;
			}
		}

	}

	private static void moveBelt(int idx, int before) { //가장 끝값을 index 0에 대입하는 것에서 시작
		if (idx == A.length) { // 끝까지 돌았을 때
			moveRobot(false); //로봇 이동시켜줌
			return;
		}
		int a = A[idx];
		A[idx] = before;
		moveBelt(idx + 1, a);
	}

	private static void moveRobot(boolean check) {
		int size = q.size(); // 현재 큐의 사이즈만큼만 돈다
		while (size-- > 0) {
			int cur = q.poll();
			if (cur + 1 == N) { //현재 칸이 끝일경우 로봇 내려감
				robot[cur] = false;
				continue;
			}
			if (check) { // 내구도 체크시
				if (A[cur + 1] < 1 || robot[cur + 1]) { // 다음칸 내구도가 1보다 작거나 다음칸에 로봇이 있을 때
					q.offer(cur); //현재 위치로 다시 넣어줌
					continue;
				}else {  // 다음칸으로 로봇 옮길 수 있을 때
					--A[cur + 1]; //내구도 체크해주기
				}
			}
			robot[cur] = false; // 현재 위치 로봇 없다
			robot[cur + 1] = true; // 다음 위치에 로봇 표시
			q.offer(cur + 1); // 다음 위치로 넣기
		}
	}

	private static void onRobot() {
		if (!robot[0] && A[0] > 0) {
			q.offer(0);
			--A[0];
			robot[0] = true;
		}
	}

	private static boolean count() {
		int cnt = 0;
		for (int i = 0, end = A.length; i < end; i++) {
			if (A[i] == 0)
				++cnt;
		}

		if (cnt >= K)
			return true;
		return false;
	}

}
