package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 램프
 */
public class BOJ1034 {
	static int K, max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if (map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
			} else {
				map.put(str, 1);
			}
		}
		K = Integer.parseInt(br.readLine());

		Iterator<String> ir = map.keySet().iterator();
		while (ir.hasNext()) {
			String str = ir.next();
			isAvailable(str, map.get(str));
		}
		System.out.println(max);
	}

	public static void isAvailable(String str, int n) {
		int c = 0;
		String[] sArr=str.split("");
		for(String s:sArr) {
			if (s.equals("0")) c++;
		}
		if(c<=K && (K-c)%2==0) {
			max=max>n?max:n;
		}
	}
}
