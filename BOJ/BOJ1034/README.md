# BOJ 1034번 [램프](https://www.acmicpc.net/problem/1034)

## 🌈 풀이 후기
- 아이디어가 중요한 문제였던 것 같습니다. 생각해내기 어렵더라구요ㅠㅠ
## 👩‍🏫 문제 풀이
- 초기에 행 별 컬럼 상태를 조사해 같은 컬럼을 가진 행이 몇개인지 구합니다.<br>
-> HashMap 이용
- 컬럼 상태가 같으면 스위치 조작 후에 컬럼 상태도 같다는 게 핵심입니다.
- HashMap을 돌며 꺼져있는 컬럼 개수를 세고, 그 개수가 K보다 작고, 뺀 값이 짝수이면 다 켜질 수 있는 행<br>
-> 해당 컬럼상태를 가지고 있는 행의 개수와 max값을 비교하여 최댓값을 구합니다.
-
 #### 핵심 코드
``` java
HashMap<String, Integer> map = new HashMap<>();
for (int i = 0; i < N; i++) {
    String str = br.readLine();
    if (map.containsKey(str)) {
        map.put(str, map.get(str) + 1);
    } else {
        map.put(str, 1);
    }
}
```
``` java
public static void isAvailable(String str, int n) {
    int c = 0;
    String[] sArr=str.split("");
    for(String s:sArr) {
        if (s.equals("0")) c++;
    }
    if(c<=K && (K-c)%2==0) {
        max=max>n?max:n;
    }
}
```