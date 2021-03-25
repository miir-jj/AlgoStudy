package jo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1077 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken());
		int W=Integer.parseInt(st.nextToken());
		int[][] val=new int[N+1][2];
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			val[i][0]=Integer.parseInt(st.nextToken());
			val[i][1]=Integer.parseInt(st.nextToken());
		}
		
		int[] knapsack=new int[W+1];
		for(int item=1;item<=N;item++) {
			int cw=val[item][0];
			int cv=val[item][1];
			for(int w=1;w<=W;w++) {
				int preVal = knapsack[w-1];
				if(w>=cw) {
					int diffVal = knapsack[w-cw];
					knapsack[w]=diffVal+cv>knapsack[w]?diffVal+cv:knapsack[w];
				}
			}
		}
		System.out.println(knapsack[W]);
	}
}
