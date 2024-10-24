package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Num2098외판원의순회 {
    static int n,INF=10_000_000;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        // dp의 의미
        // dp[3][1101] : 현재 노드가 3이고 방문하지 않은 노드가 2번이며 다시 0번 노드로 순회하는 최소 값.
        dp = new int[n][(1<<n)-1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print( recursion(0,1));
    }
    public static int recursion(int now,int check){
        if(check == (1<<n)-1) {
            // 출발점이 0 이기때문에 0 으로의 값을 더해준다.
            if(map[now][0] == 0) return INF;
            else return map[now][0];
        }
        // 그 값이 존재하면 리턴
        if(dp[now][check] != -1) return dp[now][check];
        dp[now][check] = INF;

        for(int i=0;i<n;i++){
            if(map[now][i] == 0) continue;
            // 이동된 비트값
            int next =  check | 1 << i;
            // 경로가 없거나, 현재 비트 값과 이동될 비트 값 중 1이 겹치면 x
            if(map[now][i] ==0 || (check & (1<<i)) != 0) continue;
            dp[now][check] = Math.min(dp[now][check], recursion(i,next) + map[now][i]);
        }
        return dp[now][check];
    }
}
