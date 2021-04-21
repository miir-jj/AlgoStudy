package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * 5224 구간 합
 * @author 미령
 *
 */
public class SWEA5604 {
	static long start, end, num[], ans, mul;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		for(int t = 1, T = Integer.parseInt(br.readLine().trim()); t <= T; t++) {
			num = new long[10];
			ans = 0;
			mul = 1;
			bw.write("#"+t+" ");
			st = new StringTokenizer(br.readLine()," ");
			start = Long.parseLong(st.nextToken());
			end = Long.parseLong(st.nextToken());
			sum();
			bw.write(ans + "\n");
			bw.flush();
		}
		bw.close();
	}
	private static void cal(long l) { // 자리에 해당하는 숫자마다 개수 더해줌
		String str = Long.toString(l);
		while(l > 0) {
			num[(int) (l % 10)] += mul; // 자릿수만큼 존재(100의 자리일 때 -> 각 숫자는 100개씩 존재할 것)
			l /= 10;
		}
	}
	private static void sum() {
		if(start == 0) start = 1; // while문 조건 위해
		while(start > 0) {
			while(start % 10 != 0 && start <= end) { // 끝자리가 0이 아니고, end보다 작은 동안
				cal(start); //끝자리 0이 아닐 시 각 자리수 더해줌
				++start; // start + 1
			}
			if(start > end) break; // 끝 숫자보다 커지면 break
			while(end % 10 != 9) { // 끝자리 9 아닐 시
				cal(end); // 각 자리 수 더해줌
				--end; // end - 1
			}
			long diff = (end / 10) - (start / 10) + 1; // 해당 자리수가 몇번 나왔나 ex) 1의 자리수가 100 ~ 189 사이에는 18번 나옴
			for(int i = 1; i < 10; i++) {
				num[i] += diff * mul; // 자리수 곱해서 더해줌
			}
			mul *= 10; // 다음 자리수 표시
			start /= 10; // 다음 자리수 구하기
			end /= 10;
		}
		for(int i = 1; i < 10; i++) {
			ans += i * num[i];
		}
	}
}
