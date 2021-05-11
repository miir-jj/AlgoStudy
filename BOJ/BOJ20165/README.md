# BOJ 20165번 [인내의 도미노 장인 호석](https://www.acmicpc.net/problem/20165)

## 🌈 풀이 후기
- 문제 설명대로 쭉 구현하면 됬던 문제

## 👩‍🏫 문제 풀이
- 방향이 문자로 들어오므로 해시맵으로 숫자 맵핑 ( N : 0, S : 1, W : 2, E : 3)
- domino객체 배열로 각 좌표의 도미노 높이와 쓰러졌는지 여부를 저장
- 시작 도미노의 길이를 len으로 두고, 0보다 클 동안 while문을 돈다
- 다음 도미노가 쓰러지지 않았고, 현재의 len보다 높이가 크면 len을 갱신
 #### 핵심 코드
 ```java
private static void attack(int r, int c, int d) {
    int nr = r, nc = c;
    int len = map[nr][nc].len;
    while(len-- > 0) {
        if(!map[nr][nc].flag) {
            ++score;
            map[nr][nc].flag = true;
        }
        nr+=dr[d];
        nc+=dc[d];
        if(len == 0 || nr <0 || nr >= N || nc < 0 || nc >= M) return;
        if(!map[nr][nc].flag && len < map[nr][nc].len) len = map[nr][nc].len;

    }

}
```
