# BOJ 2615번 [오목](https://www.acmicpc.net/problem/2615)

## 🌈 풀이 후기

재귀를 이용하여 풀었습니다. <br>
오목까지 확인하는 과정은 그다지 지저분하지 않은데, 육목을 확인하는 과정에서 코드가 조금 지저분해지는 것 같습니다.

## 👩‍🏫 문제 풀이

#### 함수

boolean game(int r, int c, int dir, int cnt) 현재 위치와 방향을 받아와서 그 방향으로 더해가며 오목 확인, 오목이 되었을 때 육목인지도 확인해줌

#### 핵심 코드
```java
public static boolean game(int r, int c, int dir, int cnt) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        
        if (cnt == 5) {
            if(nr > -1 && nr < 19 && nc > -1 && nc < 19 && map[r][c].equals(map[nr][nc]))//육목확인
                return false;
            return true;
        }

        if (nr < 0 || nr >= 19 || nc < 0 || nc >= 19 || !map[nr][nc].equals(map[r][c]))
            return false;
        if (game(nr, nc, dir, cnt + 1))
            return true;

        return false;
    }
```
