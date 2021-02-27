# BOJ 1325번 [효울적인 해킹](https://www.acmicpc.net/problem/1325)

## 🌈 풀이 후기
dfs로 풀었다가 메모리 초과가 나서 bfs로 풀었더니 시간초과가 났습니다......<br>
아무리 봐도 dfs에서 어떻게 메모리를 줄여아할지 모르겠어서 bfs코드를 수정했습니다.<br>
1. list보다 배열이 접근이 빠를 것 같아 이중 arraylist -> arraylist형 배열로 수정<br>
2. b->a의 입장에서 a->b 입장으로 수정

## 👩 🏫 문제 풀이
bfs메서드로 1부터N까지 컴퓨터를 해킹하며 해당 컴퓨터를 해킹했을 때 연결되어있는 컴퓨터들의 해킹 가능한 컴퓨터 수를 +1해줍니다.

#### 핵심코드
```java
public static void bfs(int computer) {
		Queue<Integer> needVisit = new LinkedList<>();
		needVisit.offer(computer);
		visited[computer] = true;

		int c;
		while (!needVisit.isEmpty()) {
			c = needVisit.poll();
			for (int i = 0, end = computers[c].size(); i < end; i++) {
				int tmp = computers[c].get(i);
				if (!visited[tmp]) {
					visited[tmp] = true;
					needVisit.offer(tmp);
					++result[tmp];
				}
			}
		}
	}
  ```
