package boj_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16637 {
	static int N;
	static Queue<Character> input;
	static Queue<Character> order;
	static int[] isSelected;
	static int result;

	public static void main(String[] args) throws Exception {
		input = new LinkedList<Character>();
		order = new LinkedList<Character>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isSelected = new int[N];
		
		String str = br.readLine();
		if (N == 1) { // 길이 1일 경우 바로 리턴
			System.out.println(str);
			return;
		}

		for (int i = 0; i < N; i++) { //수식 큐에 넣어줌
			input.offer(str.charAt(i));
		}

		// 괄호 없는 순서대로 계산한 값으로 초기값 설정
		result = input.peek() - '0';
		for (int i = 1; i < N - 1; i += 2) {
			result = calculate(result, str.charAt(i), str.charAt(i + 1) - '0');
		}
		bracket(0, 0); 
		System.out.println(result);
	}

	//괄호 경우의 수 구하기
	public static void bracket(int start, int cnt) {
		if (start >= N - 1) { //수식의 마지막 숫자 앞에는 괄호를 씌울 수 없으므로 그 전 인덱스에서 종료처리
			if (cnt != 0) { //공집합 제외
				setOrder();
				int num = order.poll() - '0';
				while (!order.isEmpty()) {
					num = calculate(num, order.poll(), order.poll() - '0');
				}
				result = Math.max(result, num);
			}
			return;
		}
		isSelected[start] = 1;
		bracket(start + 4, cnt + 1); //현재 숫자에서 괄호가 열렸으면 다음에 괄호가 열릴 수 있는 인덱스는 현재 인덱스 +4
		isSelected[start] = 0;
		bracket(start + 2, cnt); //현재 숫자에서 괄호가 열리지 않았으면 다음에 괄호 열릴 수 있는 인덱스는 현재 인덱스+2

	}

	// 괄호에 따른 계산순서대로 큐에 넣어줌
	public static void setOrder() {
		Queue<Character> copied = new LinkedList<>();
		copied.addAll(input);
		int i=0;
		while(!copied.isEmpty()) {
			if (isSelected[i] == 1) { //괄호가 열렸다면
				int result = copied.poll() - '0';
				char op = copied.poll();
				int a = copied.poll() - '0';
				order.offer((char)(calculate(result,op,a)+'0')); //먼저 계산해서 큐에 넣어줌
				i+=3;

			} else { //괄호 없을경우
				order.offer(copied.poll());
				++i;
			}
		}
	}

	// 연산 부호에 따른 계산
	public static int calculate(int result, char op, int a) {
		if (op == '+') {
			return result += a;
		} else if (op == '-') {
			return result -= a;
		} else {
			return result *= a;
		}
	}

}
