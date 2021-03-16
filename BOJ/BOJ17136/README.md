# BOJ 17136번 [색종이 붙이기](https://www.acmicpc.net/problem/17136)

## 🌈 풀이 후기
- dfs를 이용해서 풀었습니다.
- 처음에는 붙여지는 색종이 개수만 구했는데, 그 중 최솟값을 구하는 풀이가 생각나지 않아 블로그를 참조했습니다.
- 참조하고나니 생각보다 간단해서 너무 어렵게 생각했나 싶었습니다.
## 👩‍🏫 문제 풀이
- (0,0)부터 dfs를 돌려줍니다(countColored() 메서드)
- 5*5 색종이부터 차례대로 붙여보며 가능한 색종이일 때 다음 재귀로 넘어갑니다.
- 종료 조건을 짜는데 좀 복잡하다고 느꼈습니다.

 #### 변수
- int[][] map : 10*10 종이
- int[] colored : 남아있는 색종이 개수 저장 배열
 #### 핵심 코드

```
private static void countColored(int r, int c, int cnt) {
    if(r>=9&&c>9) {//마지막행 마지막열까지 검사 끝
        ans=ans<cnt?ans:cnt;//최소값 구하기
        return;
    }
    if(ans<=cnt)//이미 최소값을 넘었을 경우 더 검사할 필요x
        return;
    if(c>9) {//끝열까지 검사했을 경우
        countColored(r+1, 0, cnt);//다음 행으로 이동하여 재귀
        return;
    }
    if (map[r][c] == 1) { //색종이가 붙어있는 부분이라면
        for (int k = 5; k > 0; k--) {  5*5색종이부터 붙일 수 있는지 체크
            if (colored[k]>0&&check(r, c, k)) {
                coloring(r,c,k,0); //붙인 부분 0으로 처리
                --colored[k]; //붙인 색종이 개수 -1
                countColored(r,c+k,cnt+1); //c+k이전은 색종이 붙여진 상태이므로
                coloring(r,c,k,1);//맵 복구처리
                ++colored[k];//색종이 개수 복구
           }
        }
    }else {//색종이 안붙어있을 때
        countColored(r, c+1, cnt);//다음칸으로 이동해서 재귀
    }
}
```
