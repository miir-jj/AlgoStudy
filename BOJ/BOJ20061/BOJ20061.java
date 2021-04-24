package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 20061 모노미노도미노2
 * 빨간색 보드를 기준으로 파란색 보드와 초록색 보드에 타일을 놓는 게임
 *  -> 파란색 보드와 초록색 보드 둘다 6*4 배열로 봤다
 *  -> 파란 보드에 타일을 놓을 때는 (x, y) -> (y, x)로 좌표방향을 바꿔주고, 타일 모양도 t -> 3 - t 로 바꿔서 진행
 *  한칸 다찼을 때 -> 행 삭제 후 배열 땡겨줌
 *  특별 구역에 있을 경우 -> 타일 있는 행수만큼 아래 삭제 후 땡김
 * @author 미령
 *
 */
public class BOJ20061 {
	static int score;
	static boolean green[][], blue[][]; // 초록보드 파랑보드
	static ArrayList<Integer> gList, bList; //지워줄 행리스트

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		green = new boolean[6][4];
		blue = new boolean[6][4];
		gList = new ArrayList<Integer>(); 
		bList = new ArrayList<Integer>();

		StringTokenizer st = null;
		int t, x, y;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			t = Integer.parseInt(st.nextToken()) - 1; // 타일 0 1 2로 관리
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			addTile(t, x, y, green); //타일 놓기
			addTile(3 - t, y, x, blue);

			checkRow(green, gList); // 꽉찬 행 검사
			checkRow(blue, bList);
 
			checkBlock(green, gList); // 특별구역 검사
			checkBlock(blue, bList);

		}
		System.out.println(score);
		System.out.println(count());
	}

	private static int count() {
		int cnt = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j])
					++cnt;
				if (blue[i][j])
					++cnt;
			}
		}
		return cnt;
	}

	private static void checkBlock(boolean[][] color, ArrayList<Integer> list) {
		list.clear();
		int cnt = 0;
		for (int r = 0; r < 2; r++) {
			for (int c = 0; c < 4; c++) {
				if (color[r][c]) { //특별구역에 타일있는 행수세기
					++cnt;
					break;
				}
			}
		}
		for (int i = 5 - cnt; i >= 0; i--) { // 행수 바로 윗줄부터 복사처리
			System.arraycopy(color[i], 0, color[i + cnt], 0, 4);
		}
		for (int i = 0; i < 2; i++) { // 특별구역 초기화
			Arrays.fill(color[i], false);
		}
	}

	private static void checkRow(boolean[][] color, ArrayList<Integer> list) {
		list.clear(); // 초기화
		boolean flag = true;
		for (int r = 2; r < 6; r++) {
			flag = true;
			for (int c = 0; c < 4; c++) {
				if (!color[r][c]) {
					flag = false; //모든 열에 블록있는 것이 아니면 안지움
					break;
				}
			}
			if (flag) //모든 열에 블록이 있으면
				list.add(r); //지울 행 리스트에 추가
		}
		score += list.size(); //점수에 더해줌
		for (int l : list) {
			for (int i = l - 1; i >= 0; i--) {
				System.arraycopy(color[i], 0, color[i + 1], 0, 4); // 지운줄 바로 위부터 행을 아래행에 복사
			}
		}
		for (int i = 0, end = list.size(); i < end; i++) {
			Arrays.fill(color[i], false); //위에서부터 지운 행 수 만큼 초기화
		}
	}

	private static void addTile(int t, int x, int y, boolean[][] color) {
		int r = -1;
		for (int i = 0; i < 6; i++) {
			if (color[i][y]) // 해당 열에 블록이 있는지
				break; 
			if (t == 1 && color[i][y + 1]) //1 * 2 타입일 때는 다음 열도 검사
				break;
			++r; // 행에 블록 없을 때는 +1
		}

		switch (t % 3) { // 0타입일 때 3이 들어오므로
		case 0:
			color[r][y] = true;
			break;
		case 1:
			color[r][y] = true;
			color[r][y + 1] = true;
			break;
		case 2:
			color[r][y] = true;
			color[r - 1][y] = true;
			break;
		}
	}
}
