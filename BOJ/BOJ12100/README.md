# BOJ 12100번 [2048(Easy)](https://www.acmicpc.net/problem/12100)

## 🌈 풀이 후기
- 코드는 길지만 난이도에 비해 쉽게 풀렸던 문제였습니다.
## 👩‍🏫 문제 풀이
   * 5번 이동시킬 수 있는 모든 경우의 수 구함 -> 중복순열
   * 각 방향에 대해서 시작점과 다음 행 혹은 열로 넘어가게 될 값을 설정해줌
   * 맵을 넘어가지 않을 때 까지 각 열/행을 검사
   * 다섯번 모두 이동한 후 최댓값 구하기
#### 핵심 코드
``` java
for (int i = 0; i < 5; i++) {
    init(dir[i]); // 현재 방향 설정
    while (isIn(sr, sc)) { // 시작점이 맵을 넘지 않았을 때
        int before = 0; // 초기값 0
        int r, c;
        for (int j = 0; j < N; j++) {
            r = sr + dr[dir[i]] * j; //다음행/열을 차례로 검사
            c = sc + dc[dir[i]] * j;
            if (tmap[r][c] == 0) // 0이면 스킵
                continue;
            if (before == 0) // 전값이 0이면 -> 처음이거나 두칸의 값이 같아 큐에 들어간 경우
                before = tmap[r][c]; // 현재 값으로 설정해줌
            else {
                if (before == tmap[r][c]) { // 전 값과 현재 값이 같으면
                    q.offer(before + before); // 큐에 더한값 삽입
                    before = 0; // 전 값 초기화
                } else {
                    q.offer(before); // 같지 않으면 전 값 큐에 삽입
                    before = tmap[r][c]; // 현재값으로 초기화
                }
            }
            tmap[r][c] = 0; // 맵 초기화
        }
        if (before != 0) //마지막 검사 값 남아있을 경우
            q.offer(before); //큐에 넣어줌
```