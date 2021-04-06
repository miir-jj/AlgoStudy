# BOJ 17281번 [⚾](https://www.acmicpc.net/problem/17281)

## 🌈 풀이 후기
- 야구를 구현하는 문제
- 야구룰을 구현하는 것은 코드가 길지만 어렵지는 않았습니다.
- 4번타자가 정해져있는 것을 깜빡하고 나중에 수정했습니다.

## 👩‍🏫 문제 풀이
- 비트마스크를 이용해서 주자 순서를 정하고, 4번타자는 무조건 1번 선수이기 때문에 <br>
  그외의 경우에는 게임을 진행하지 않고 리턴해주었습니다.
 #### 변수
- int[] order : 선수 순서를 정할 배열
- int[][] result : 각 이닝별 결과가 저장된 배열
- boolean[] base : 각 루에 선수가 있는지 표시한 배열

 #### 핵심 코드
 ```
private static void combi(int idx, int start, int flag) {
    if (idx == 9) {
        if (order[3] != 0)
            return;
        int score = game();
        max = max > score ? max : score;
        return;
    }
    for (int i = 0; i < 9; i++) {
        if ((flag & 1 << i) != 0)
            continue;
        order[idx] = i;
        combi(idx + 1, start + 1, flag | 1 << i);
    }
}
```
