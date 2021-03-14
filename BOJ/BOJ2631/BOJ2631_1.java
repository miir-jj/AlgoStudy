package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
/**
 * BOJ2631 줄세우기 - 가장 긴 수열 요소까지 구하기
 * https://www.acmicpc.net/problem/2631
 */
public class BOJ2631_1 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		int N=Integer.parseInt(br.readLine());
		int n=0,l=0; //n:입력받을 수 , l:dp의 길이
		int[] num=new int[N];
		LinkedList<Integer> dp=new LinkedList<>();
		Stack<Integer> lis=new Stack<>();
		dp.add(0);
		
		int[] I=new int[N]; // 저장된 위치 표시 배열
		
		for(int i=0;i<N;i++) {
			n=Integer.parseInt(br.readLine());
			num[i]=n;
			l=dp.size();
			if(dp.get(l-1)<n) {
				dp.add(n);
				I[i]=l;
			}else {
				int index=binarySearch(dp,n)+1;
				dp.remove(index);
				dp.add(index, n);
				I[i]=index;
			}
		}
		System.out.println(dp.toString());
		System.out.println(N-(dp.size()-1));
		
		lis=LIS(dp.size()-1,I,num);
		while(!lis.isEmpty()) {
			System.out.print(lis.pop()+" ");
		}
	}

	private static Stack<Integer> LIS(int size,int[] I,int[] N) {
		int len=size;
		Stack<Integer> s=new Stack<>();
		for(int i=I.length-1;i>=0;i--) {
			if(I[i]==len) {
				s.push(N[i]);
				len--;
			}
		}
		return s;
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
