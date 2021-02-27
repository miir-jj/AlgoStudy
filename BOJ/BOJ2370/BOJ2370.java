package BOJ2370;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Poster implements Comparable<Poster> {
	int start, end;

	public Poster(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Poster w) {
		return this.start - w.start;
	}

}

public class BOJ2370 {
	static Poster[] posters;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Poster[] posters = new Poster[N];
		ArrayList<Integer> input=new ArrayList<>();

		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			posters[n] = new Poster(a,b);
			if(!input.contains(a)) input.add(a);
			if(!input.contains(b)) input.add(b);
		}
		
		Collections.sort(input);
		
		boolean isUpdated;
		int ans=0,flag=0;
		for(int i=N-1;i>=0;i--) {
			isUpdated=false;
			for(int j=input.indexOf(posters[i].start),end=input.indexOf(posters[i].end);j<=end;j++) {
				if((flag&1<<j)!=0) continue;
				isUpdated=true;
				flag=flag|1<<j;
			}
			if(isUpdated) ++ans;
		}
		System.out.println(ans);
	}
}
