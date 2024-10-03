package HiJaeYoung.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Input
    체스판의 크기 N <= 10, 비숍을 놓을 수 있는 곳 1, 없는 곳 0
Output 10sec
    비숍을 놓을 수 있는 최대의 수

1. BackTracking
    - 그룹화
        특정 한 점에 비숍을 두었을 때 비숍을 둘 수 없는 부분은 x+n , y+n 집합.
    - List<List<Location>>
        - (x,y) 가 비숍을 둘 수 있는 자리면 lis의 0번째 요소를 하나씩 탐색
        - 새로운 비숍 집합이면 새로운 리스트에 추가.
            - ! 한 집합에 서로다른 두 장소에 퀀을 둘 수 있음
    - 이제 백트래킹

1.1 재도전
    - 그룹화
        - 모든 비숍가능 자리에 대하여 같이 둘 수 없는 그룹을 지정
        - 그룹의 수가 적도록 오름차순
        - 만일 그룹의 수가 2이상
            각 요소의 그룹 사이즈를 비교
            사이즈가 같고 요소가 같으면 더 빠른 인덱스 선택
            사이즈가 같고 요소가 다르면 백트래킹으로 검증
                !백트래킹..을 하기에는 분기점 잡기가 곤란쓰
2. 그리디
    - 실 패
3. 대각선으로 쪼개기
    - 놓을 수 있는 비숍의 수는 하나의 대각선 당 최대 하나이다.
    - 시간 초과 -> 함수 구조변경
    - 만일 n개의 후보가 둘다 불가능한 경우 함수가 끝나버림
        5
        1 0 0 1 0
        1 0 0 0 1
        0 0 0 0 0
        0 0 1 0 0
        0 0 1 0 0
    - List의 마지막 index를 지우는 것이 틀림. 모든 과정을 백트래킹을 하지 않기때문
    - 대각선 내 하나의 자리만 있는 경우에도 백트래킹을 해야할까?
    - n = 1인경우
        1
        1
    - 대각선의 시작이 달라지면 최대 최소 값이 달라진다? 그럴리가

3. 유형 나누기
    - 체스의 특징 흑백을 이용하여 생각.
        - (i+j) % 2 = 0
        - (i+j) % 2 = 1

*/
public class Num1799_비숍 {
    static int n,result;
    static int[][] map;
    static boolean[] check;
    static final int BLACK = 1, WHITE = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
        }
        int ans = 0;
        for(int i=WHITE;i<=BLACK;i++){
            result = 0;
            search(i,0,0,new boolean[n*n],new boolean[n*n]);
            ans += result;
        }
        System.out.println(ans);
    }
    public static void search(int color, int index,int cnt,boolean[] booleans1,boolean[] booleans2){
        if(index == n*n){
            result = Math.max(result,cnt);
            return;
        }
        int i = index/n;
        int j = index%n;
        int boolean2Index = boolean2Index(i,j);
        if((i+j)%2==color && map[i][j]==1 && !booleans1[i+j] && !booleans2[boolean2Index]){
            booleans1[i+j] = true;
            booleans2[boolean2Index] = true;
            search(color,index+1,cnt+1,booleans1,booleans2);
            booleans1[i+j] = false;
            booleans2[boolean2Index] = false;
        }
        search(color,index+1,cnt,booleans1,booleans2);
    }
    public static int boolean2Index(int i,int j){
        if(j>=i) return j-i;
        return i-j + n;
    }
}
