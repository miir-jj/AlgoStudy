package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * BOJ2631 줄세우기
 * https://www.acmicpc.net/problem/2631
 */
public class BOJ2631 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		int N=Integer.parseInt(br.readLine());
		int n=0,l=0; //n:입력받을 수 , l:dp의 길이
		LinkedList<Integer> dp=new LinkedList<>();
		dp.add(0);
		
		for(int i=0;i<N;i++) {
			n=Integer.parseInt(br.readLine());
			l=dp.size();
			if(dp.get(l-1)<n) {
				dp.add(n);
			}else {
				int index=binarySearch(dp,n)+1;
				dp.remove(index);
				dp.add(index, n);
			}
		}
		System.out.println(N-(dp.size()-1));
	}

	private static int binarySearch(LinkedList<Integer> dp, int n) {
		int l = 1;
		int r = dp.size() - 1;
		int mid=0;
		int index = 0;

		while (l <= r) {
			mid = (l + r) / 2;
			if (n < dp.get(mid)) {
				r = mid - 1;
			} else {
				index = mid;
				l = mid + 1;
			}
		}
		return index;
	}
}
