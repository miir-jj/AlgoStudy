# BOJ 2370번 [시장 선거 포스터 ](https://www.acmicpc.net/problem/2370)

## 🌈 풀이 후기
처음에는 비트마스크로 맨뒤의 포스터부터 왼쪽 끝부터 오른쪽 끝까지 검사하며, 해당자리가 0이면 1로 update해주고<br>
아니면 continue 하게해 마지막에서 update됬으면 포스터수+1 하도록 해주었습니다.<br>
너비가 이렇게 넓은 줄은 몰랐습니다.....시간초과 났어요ㅠㅠ<br><br>
너비를 줄일 방법을 생각하다가 포스터의 왼쪽 끝과 오른쪽 끝을 입력받아 arraylist로 만든뒤(중복값 생략) 오름차순으로 정렬해주고,<br>
맨 마지막 포스터부터 왼쪽 끝이 들어있는 요소부터 오른쪽 끝이 들어있는 요소까지 마스킹 해주고 나머지 알고리즘은 똑같이 풀어줬습니다.<br>
근데 틀렸어영......반례나 어느부분을 놓친건지 아시겠는분 알려주세요ㅠㅠ


## 👩‍🏫 문제 풀이
#### 핵심코드
```java
boolean isUpdated;      //
		int ans=0,flag=0;
		for(int i=N-1;i>=0;i--) {    //맨 마지막에 붙여진 포스터부터
			isUpdated=false;
			for(int j=input.indexOf(posters[i].start),end=input.indexOf(posters[i].end);j<=end;j++) { // 왼쪽 끝이 들어있는 인덱스와 오른쪽 끝이 들어있는 인덱스를 가져옴
				if((flag&1<<j)!=0) continue; //이미 포스터 붙어있으면 continue
				isUpdated=true; //안붙어있으면 update true로 바꿔주고
				flag=flag|1<<j; //마스킹처리
			}
			if(isUpdated) ++ans; //마스킹처리 하나라도 되었으면 포스터 앞에서 보임 -> ans+1
		}
```
