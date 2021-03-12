# BOJ 2629번 [양팔저울](https://www.acmicpc.net/problem/2629)

## 🌈 풀이 후기
- dp를 연습해보려고 골라본 문제였습니다!
- 지난번 개근상 문제풀이 방법을 생각해보며 풀어봤습니다. 생각보다 간단하게 풀리는 문제였습니다!
## 👩‍🏫 문제 풀이
- 구슬이 1 4 있을 때 추측할 수 있는 무게<br>

    1|4
    ---|---
    -|4+1
    -|4-1

 - 구슬이 1 4 6 있을 때 추측할 수 있는 무게의 경우<br>

    1|4|6
    ---|--- |---
    -|1+4|1+6
    -|1-4|1-6
    -|-|4+6
    -|-|4-6
    -|-|1+4+6
    -|-|1+4-6
    -|-|1-4+6
    -|-|1-4-6

 - 즉 n번째 구슬의 무게 + (n-1번째 까지 경우들의 무게 +w) + (n-1번째 까지 경우들의 무게 -w)

 #### 변수
 - sinker(Set) : 추측할 수 있는 무게들을 저장할 set
 - tmp(Set) : n번째에서 추가될 무게들을 저장할 set
     -  sinker 하나로 돌렸더니 set의 사이즈가 계속 변하는 것을 생각안해줘서 tmp에 저장후 sinker에 합쳐줍니다.

 #### 핵심 코드
 ```
public static void dp(int w, HashSet<Integer> sinker, HashSet<Integer> tmp) {
		Iterator<Integer> ir = sinker.iterator();
		while (ir.hasNext()) {
			int n = ir.next();
			tmp.add(n + w); //n-1번째 까지의 구해진 무게들에 +-w 해서 tmp에 넣어줌
			tmp.add(Math.abs(n - w)); //음수는 절댓값 처리!
		}
		tmp.add(w); //자기자신무게 넣어준 후
		sinker.addAll(tmp); // sinker에 합해주고
		tmp.clear(); //tmp 비움
	}
```
