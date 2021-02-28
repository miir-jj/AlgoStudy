package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1300_1 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int K=Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)-> {return b-a;});
		for(int i=1;i<=N;i++) {
			for(int j=i;j<=N;j++) {
				int n = i*j;
				if(n>K) break;
				if(pq.size()>=K) {
					if(pq.peek()>n) {
						pq.offer(n);
						while(pq.size()>K)
							pq.poll();
					}
				}else {
					if(i==j) {
						pq.offer(n);
					}else {
						pq.offer(n);
						pq.offer(n);
					}
				}
			}
		}
		System.out.println(pq.poll());
	}
}
