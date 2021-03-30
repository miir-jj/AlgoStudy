# BOJ 17609번 [회문](https://www.acmicpc.net/problem/17609)

## 🌈 풀이 후기
- 처음에 while문으로 짰다가 틀리고 재귀로 다시 짰습니다. 시간이 좀 오래걸리는 듯 한데 다른 방법은 생각이 안나요..!
## 👩‍🏫 문제 풀이
- 처음에는 while문으로 p1<=p2인 동안 돌며 각 위치의 문자가 동일하지 않을 때
둘중에 한 포인터만 한자리 앞으로 가서 비교한 후 일치하면 다시 반복을 도는 방식으로 풀었습니다
- 왼쪽에서 한칸 앞으로 왔을경우, 오른쪽에서 한칸 앞으로 왔을 경우 두 경우 모두 생각해야한다는 것을 알고
재귀로 다시 풀었습니다.

 #### 변수
- p1 : 왼쪽 포인터
- p2 : 오른쪽 포인터
- cnt : 유사회문 여부 카운트 할 변수
 #### 핵심 코드
 ```
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
```
