# BOJ 2075번 [N번째 큰 수](https://www.acmicpc.net/problem/2075)

## 🌈 풀이 후기
- 처음엔 어떻게 풀어야하나 막막했는데, 알고리즘 분류가 우선순위 큐로 분류되어있는 것에서 힌트를 얻었습니다.

## 👩‍🏫 문제 풀이
- 반복문을 돌며 입력 n을 받고 <br>
priority queue의 size가 N이상이 되었을 때, peek()한 값이 n보다 작으면 n을 offer()후 poll()을 한번 수행해줍니다.<br>
그 외의 경우에는 n을 offer()해주고, 반복이 끝난 후에 queue의 맨 앞 숫자를 출력합니다.
