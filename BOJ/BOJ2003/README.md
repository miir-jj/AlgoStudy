# BOJ 2003번 [수들의합2](https://www.acmicpc.net/problem/2003)

## 🌈 풀이 후기
한 원소의 값이 M일 경우를 생각하지 않아 1번 틀렸습니다. 조건을 좀 더 꼼꼼하게 짤 수 있도록 하겠습니다.


## 👩‍🏫 문제 풀이

마지막 원소까지 while문을 돌며 각 조건에 대해 변수초기화를 해줍니다.

#### 핵심코드
```
while (end < N) { //마지막 원소까지 돌며
			if(num[end]==M) { //해당 원소가 M값이면
				++cnt; //경우 수에 +1
				sum=0; //합변수 초기화
				start=++end; //end변수 +1, start변수 end로 초기화
				continue;
			}		
			if (sum < M) { //sum이 M보다 작을 경우
				sum += num[end++]; //현재 변수 더해주고 end +1
			} else if(sum>M){ //sum이 M보다 클경우
				sum-=num[start++]; //맨 앞 변수 빼주고 start +1
			}
			if (sum == M) { //M일 경우
				++cnt; //경우 수에 +1
				sum-=num[start++]; //맨 앞 변수 빼주고 start +1
			}
		}
```
