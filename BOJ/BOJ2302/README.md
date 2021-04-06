# BOJ 2302번 [극장좌석](https://www.acmicpc.net/problem/2302)

## 🌈 풀이 후기
- dp문제
- 점화식을 알고나니까 쉬웠는데 안구해져서 블로그 참고했습니다......ㅠ

## 👩‍🏫 문제 풀이
- N이 자기자리 앉을 때는 N-1까지 잘 앉아있으면 되고, N-1자리에 앉을 때는 N-2까지 잘 앉아있으면 됩니다.
- 그러므로 dp[N] = dp[N-1] + dp[N-2]
- vip좌석을 제외하고 각 구간 경우의 수를 세서 곱해줍니다.
 #### 핵심 코드
 ```java
long[] dp = new long[41];
dp[1] = 1;
dp[2] = 2;
for (int i = 3; i <= 40; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}

int n=0;
long ans=1;
for(int i=1;i<=N;i++) {
    if(num.contains(i)) {
        if(n==0) continue;
        ans*=dp[n];
        n=0;
    }else ++n;
}
if(n!=0) ans*=dp[n];
System.out.println(ans);
```
