package BOJ14719;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {
	static int H, W;
	public static int[] height;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		height = new int[W];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		int h1, h2, tmp=0, result=0;
		for(int i=1;i<W-1;i++) {
			h1=0;
			h2=0;
			for(int j=0;j<i;j++) {
				h1=Math.max(h1, height[j]); //현재 위치 제외 양옆의 높이 최댓값을 구함
			}
			for(int j=i+1;j<W;j++) {
				h2=Math.max(h2, height[j]);
			}
			tmp=(Math.min(h1,h2)-height[i]); //양 옆 높이 중 최솟값에서 현재 높이 빼줌
			if(tmp>0)	////빗물이 저장 될 경우
				result+=tmp;
		}
		System.out.println(result);
	}




}
