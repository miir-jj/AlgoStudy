package BOJ0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ2504 {
	static Stack<Character> open = new Stack<Character>();
	static Stack<Integer> num = new Stack<Integer>();
	static String input;
	static int result;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> open=new Stack<Character>();
		input = br.readLine();
		
		char ch;
		int tmp=1, result=0;
		boolean check=true;

		for (int i = 0; i < input.length(); i++) {
			ch=input.charAt(i);
			switch (ch) {
			case '(':
				open.push(ch);
				tmp*=2;
				break;
			case '[':
				open.push(ch);
				tmp*=3;
				break;
			case ')':
				if(open.isEmpty()||open.peek()!='(') {
					check=false;
					break;
				}
				if(input.charAt(i-1)=='(') { //연속된 괄호일 경우에만 result 더해줌(중첩된 모든 괄호가 닫힘을 의미)
					result+=tmp;
				}
				open.pop();
				tmp/=2;
				break;
			case ']':
				if(open.isEmpty()||open.peek()!='[') { //런타임 에러 peek을 먼저 할경우
					check=false;
					break;
				}
				if(input.charAt(i-1)=='[') {
					result+=tmp;
				}
				open.pop();
				tmp/=3;
				break;
			}
		}
		if(check&&open.empty())//괄호가 열리고 닫히지 않은 경우
			System.out.println(result);
		else
			System.out.println(0);
	}

}
