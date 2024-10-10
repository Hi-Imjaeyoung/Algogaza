package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
수열이 팰린드롬이 될 수 있도로 추가할 숫자의 최소 개수
N 5000

1. 가운데 부터 투 포인터로 하나씩 이동.
    - 가운데 포인트를 어떻게 잡을 것?
        다 잡기.
        5000 + 4999 + ...+ 1  = 5000 * 2500 < 10^8
        1 2 1 9 9 4 3 3 3 2 1 일때 처리 어떨게?
        탐색 중 숫자가 다른 경우
        1. 오른쪽에 그 숫자를 추가
        2. 왼쪽에 그 숫자를 추가

4
1 1 2 2
-> 2 2 1 1 2 2
ans 2개

- mid가 없을 수 도 있다.
*/
public class Num1695팰린드롬_만들기 {
    static int n;
    static int[] arr;
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int [n];
        int answer = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        memo = new int[n+2][n+2];
        for(int i=0;i<n+2;i++){
            Arrays.fill(memo[i],Integer.MAX_VALUE);
        }
        if(arr[n-1] == arr[0]){
            memo[1][n-1] = 0;
        }
        for(int i = 0;i<n;i++){
            find(i-1,i);
            find(i-1,i+1);
            answer = Math.min(memo[i][i+1],answer);
            answer = Math.min(answer,memo[i][i]);
        }
        System.out.println(answer);
    }
    public static int find(int lPoint, int rPoint){
    //        System.out.println(lPoint+" "+rPoint);
        if(memo[lPoint+1][rPoint] !=Integer.MAX_VALUE) return memo[lPoint+1][rPoint];
        if(rPoint >= n ) {
            memo[lPoint+1][rPoint] = lPoint+1;
            return lPoint+1;
        }
        if(lPoint < 0) {
            memo[lPoint+1][rPoint] = n-rPoint;
//            System.out.println(lPoint+" "+(n-rPoint));
            return n-rPoint;
        }
        if(arr[lPoint] != arr[rPoint]){
            int cnt1= find(lPoint-1, rPoint);
            int cnt2= find(lPoint, rPoint+1);
            if(cnt1 >= cnt2){
                memo[lPoint+1][rPoint] = cnt2+1;
            }else{
                memo[lPoint+1][rPoint] = cnt1+1;
            }
        }else{
            memo[lPoint+1][rPoint] = find(lPoint-1,rPoint+1);
        }
        return memo[lPoint+1][rPoint];
    }
}
