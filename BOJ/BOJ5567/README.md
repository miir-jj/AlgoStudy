# BOJ 5567번 [결혼식](https://www.acmicpc.net/problem/5567)

## 🌈 풀이 후기
- bfs를 응용해서 풀었습니다
- 양방향 그래프라고 문제에 써줬는데 체크안해서 한번 틀렸습니다ㅠㅠ

## 👩‍🏫 문제 풀이
- bfs를 레벨별로 돌아줍니다.
- 친구, 친구의 친구만 돌면 되므로 본인 포함 3번 돌며 같은 레벨에서 들어온 노드를 한번에 돌아줍니다.

 #### 핵심 코드
 ```java
private static int bfs() {
    Queue<Integer> q=new LinkedList<Integer>();
    boolean[] visited=new boolean[N];
    visited[0]=true;
    q.offer(0);
    int cnt=0,cur;

    for(int t=0;t<2;t++) {
        int size=q.size();
        while(size-->0) {
            cur=q.poll();
            for(int i:graph[cur]) {
                if(!visited[i]) {
                    visited[i]=true;
                    q.add(i);
                    ++cnt;
                }
            }
        }
    }
    return cnt;
}
```
