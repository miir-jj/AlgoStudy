# BOJ 20291번 [파일정리](https://www.acmicpc.net/problem/20291)

## 🌈 풀이 후기
- 문자열 문제를 풀어보고 싶어서 골라 본 문제였습니다.
- .으로 split이 안되고 \\. 으로 해줘야 한다는 것을 알게되었습니다.

## 👩‍🏫 문제 풀이
- .을 기준으로 split하여 배열에 넣어준 후 가장 마지막 요소를 map에 넣는다.
- 각 확장자 별로 개수를 센 후 keySet을 리스트로 만들어 정렬 후 value값을 출력한다.
 #### 핵심 코드
 ```java
List<String> keyList = new ArrayList<String>(map.keySet());
Collections.sort(keyList);
```
