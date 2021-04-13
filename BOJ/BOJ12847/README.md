# BOJ 12847번 [꿀 아르바이트](https://www.acmicpc.net/problem/12847)

## 🌈 풀이 후기
- 누적합을 구하는 간단한 문제였습니다.
- long자료형으로 해주지 않아 여러번 틀렸습니다ㅠㅠ
## 👩‍🏫 문제 풀이
- Queue에 입력받은 수를 넣어주고 합을 구합니다
- size가 m이 넘으면 합에서 큐의 poll()한 값을 빼주고, 최댓값을 구합니다.
 #### 핵심 코드
 ```java
Queue<Integer> q = new LinkedList<Integer>();
st = new StringTokenizer(br.readLine(), " ");
long sum = 0, max= 0;
int cur = 0;
for (int i = 0; i < n; i++) {
    cur = Integer.parseInt(st.nextToken());
    q.offer(cur);
    sum += cur;
    if(q.size()>m) {
        sum = sum - q.poll();
    }
    max = max > sum ? max : sum;
}
```
