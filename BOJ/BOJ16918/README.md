# BOJ 16918번 [봄버맨](https://www.acmicpc.net/problem/16918)

## 🌈 풀이 후기
- 처음 4초의 상태만 반복되는 줄 알고 짰다가 틀린 거 알고 고치다가 꼬여서 여러번 틀렸습니다ㅠㅠ
## 👩‍🏫 문제 풀이
- 짝수번째는 빈자리에 폭탄을 다 채워주므로 O로 채워진 map을 출력
- N>2일 때, 현재 저장된 폭탄의 위치로 bfs로 사방탐색하며 파괴될 곳을 bombList에 같이 체크해둔다
- N%4==1일 때는 초기 폭탄이 터지고 난 뒤 설치된 폭탄이 터진 경우로 생각해주어야함
 - findBomb함수로 이전에 터진 곳들 제외한 부분들을 bombList에 체크하고 인접 파괴부분 bfs로 체크해줌
- map 전체 O로 채워놓고 bombList에 저장된 공간은 .으로 표시
 #### 변수
- LinkedList<Integer>[] bombList : 행마다 폭탄 위치 저장해놓은 인접리스트

 #### 핵심 코드
 ```
private static void findBomb() {
    for (int r = 0; r < R; r++) {
        tmp = new ArrayList<Integer>();
        for (int c = 0; c < C; c++) {
            if (!bombList[r].contains(c)) {
                tmp.add(c);
            }
        }
        bombList[r].clear();
        bombList[r].addAll(tmp);
    }
    bfs();
}
private static void bfs() {
    ArrayList<Pair> b = new ArrayList<Pair>();
    boolean[][] visited = new boolean[R][C];
    int nr, nc;
    for (int r = 0; r < R; r++) {
        for (int c : bombList[r]) {
            visited[r][c] = true;
            for (int d = 0; d < 4; d++) {
                nr = r + dr[d];
                nc = c + dc[d];
                if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc]) {
                    b.add(new Pair(nr, nc));
                }
            }
        }
    }
    for (Pair p : b) {
        bombList[p.r].add(p.c);
    }
}
```
