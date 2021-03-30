package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 회문 https://www.acmicpc.net/problem/17609
 */
public class BOJ17609 {
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		String str;
		for (int t = 0; t < T; t++) {
			ans=2;
			str=br.readLine();
			palindrome(str,0,str.length()-1,0);
			bw.write(String.valueOf(ans));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	private static void palindrome(String str,int p1,int p2,int cnt) {
		if(cnt>1)
			return;
		if(p1>p2) {
			if(cnt==1) ans = 1;
			else ans = 0;
			return;
		} 
		if(str.charAt(p1)==str.charAt(p2)) {
			palindrome(str, p1+1, p2-1, cnt);
		}else {
			if(str.charAt(p1 + 1) == str.charAt(p2)) {
				palindrome(str,p1+2,p2-1,cnt+1);
			}
			if(str.charAt(p1) == str.charAt(p2 - 1)) {
				palindrome(str, p1+1, p2-2, cnt+1);
			}
		}
		return;
	}

//	private static int palindrome(String str) {
//		int p1 = 0, p2 = str.length() - 1;
//		int cnt = 0;
//		while (p1 <= p2) {
//			if (str.charAt(p1) == str.charAt(p2)) {
//				++p1;
//				--p2;
//				continue;
//			} else {
//				if (cnt == 1)
//					return 2;
//				if (str.charAt(p1 + 1) == str.charAt(p2)) {
//					p1 += 2;
//					--p2;
//					++cnt;
//					continue;
//				} else if (str.charAt(p1) == str.charAt(p2 - 1)) {
//					++p1;
//					p2 -= 2;
//					++cnt;
//					continue;
//				}else {
//					return 2;
//				}
//			}
//		}
//		if(cnt==1) return 1;
//		return 0;
//	}
}
