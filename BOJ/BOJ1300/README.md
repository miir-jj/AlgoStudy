# BOJ 1300번 [K번째 수](https://www.acmicpc.net/problem/1300)

## 🌈 풀이 후기
- 처음엔 2075번 문제와 비슷하겠다 싶어 우선순위 큐를 이용해서 풀었는데 수 범위가 커서 메모리 초과가 났습니다.
- binary search를 어떻게 적용시켜야 하나 이틀이 지나도 감이 안잡혀 블로그 코드를 참고했습니다.

## 👩‍🏫 문제 풀이
- low=1, high=K로 초기화 한 후 중간값(mid)를 기준으로 mid보다 작은 수의 개수를 구합니다.
- 그 수가 K보다 작으면 low=mid+1, 크면 high=mid-1을 해줍니다.

#### 핵심코드
```
while (low <= high) {
			mid = (low + high) / 2;
			cnt = 0;
			for (int i = 1; i <= N; i++) {
				cnt += mid / i < N ? mid / i : N;
			}

			if (cnt < K)
				low = mid + 1;
			else {
				ans=mid; //ans값 왜 저장하는지 이해 가지 않았었음 -> cnt가 이후 mid에서 K보다 작아질 수 있으므로 저장해두어야함
				high = mid - 1;
			}
		}
```
