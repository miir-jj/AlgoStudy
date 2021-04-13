# BOJ 2564번 [경비원](https://www.acmicpc.net/problem/2564)

## 🌈 풀이 후기
- 오타에 주의해야겠습니다.
- StringTokenizer가 반복되서 간단히 하는 방법을 찾아봐야겠습니다.
## 👩‍🏫 문제 풀이
- 동서남북에 따라 좌표값을 지정해서 방향별로 리스트에 저장해줍니다
- 북 : (0,n)
- 남 : (R,n)
- 동 : (n,0)
- 서 : (n,C)
- 수평방향에 위치한 상점은 양 끝단에서의 최솟값을 구해서 더해줍니다.
- 그리고 모든 경우에서 좌표사이의 거리를 더해줍니다.
 #### 핵심 코드
``` java=
for (int i = 1; i <= 4; i++) {
    for (int[] s : store[i]) {
        if (i == dir[a]) {
            ans += getDistance(dir[a], s, dong) * 2;
        }
        ans += Math.abs(dong[0] - s[0]) + Math.abs(dong[1] - s[1]);
    }
}
System.out.println(ans);
```
```java=
private static int getDistance(int dir, int[] s, int[] dong) {
    int min;
    if (dir == 1 || dir == 2) {
        min = dong[1] < s[1] ? dong[1] : s[1];
        min = min < C - dong[1] ? min : C - dong[1];
        min = min < C - s[1] ? min : C - s[1];
    } else {
        min = dong[0] < s[0] ? dong[0] : s[0];
        min = min < R - dong[0] ? min : R - dong[0];
        min = min < R - s[0] ? min : R - s[0];
    }

    return min;
}
```
```java=
public static int[] getLocation(int a, int b) {
    switch (a) {
    case 1:
        return new int[] { 0, b };
    case 2:
        return new int[] { R, b };
    case 3:
        return new int[] { b, 0 };
    default:
        return new int[] { b, C };
    }
}
```