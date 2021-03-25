package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA1486 {
	static int N,B,min;
	static int[] h;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=null;
		
		int T=Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			bw.write("#"+t+" ");
			min=Integer.MAX_VALUE;
			st=new StringTokenizer(br.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			B=Integer.parseInt(st.nextToken());
			h=new int[N];
			
			st=new StringTokenizer(br.readLine()," ");
			for(int i=0;i<N;i++) {
				h[i]=Integer.parseInt(st.nextToken());
			}
			subset(0,0,0);
			bw.write((min-B)+"\n");
			bw.flush();
		}
		bw.close();
	}
	private static void subset(int start,int sum,int flag) {
		if(sum>min)
			return;
		if(sum>=B) {
			min=min<sum?min:sum;
			return;
		}
		for(int i=start;i<N;i++) {
			if((flag&1<<i)!=0) continue;
			subset(i+1,sum+h[i],flag|1<<i);
		}
	}

}
