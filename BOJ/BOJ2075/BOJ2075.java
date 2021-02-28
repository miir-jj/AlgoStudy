package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2075 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		PriorityQueue<Integer> num=new PriorityQueue<>();
		
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				int n=Integer.parseInt(st.nextToken());
				if(num.size()>=N) {
					if(num.peek()<n) {
						num.offer(n);
						num.poll();
					}
				}else {
					num.offer(n);
				}
			}
		}
		System.out.println(num.poll());
	}
}
