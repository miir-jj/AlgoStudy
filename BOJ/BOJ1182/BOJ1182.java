package BOJ0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {

	public static void main(String[] args) throws IOException {
		int N, S;
		int[] num;
		int result=0;
		int sum=0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		
		num = new int[N];
		
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1, end = 2 << (N - 1); i < end; i++) {//0인 경우 제외하고
			sum=0;
			for (int j = 0; j < N; j++) {
				if((i & 1 << j) != 0) {
					sum+=num[j];
				}
			}
			if(sum==S) {
				result++;
				System.out.println(i);
			}
		}
		System.out.println(result);

	}

}
