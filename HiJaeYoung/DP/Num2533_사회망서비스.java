package HiJaeYoung.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Num2533_사회망서비스 {
    static int n;
    static List<Integer>[] map;
    static int[][] dp;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            map[i] = new ArrayList<>();
        }
        for(int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            map[point1].add(point2);
            map[point2].add(point1);
        }
        dp = new int[2][n+1];
        visit = new boolean[n+1];
        dfs(1,0);
        System.out.println(Math.min(dp[0][1],dp[1][1]));
    }
    public static void dfs(int node,int beforeNode){
        if(visit[node]) return;
        visit[node] = true;
        dp[1][node] = 1;
        for(int next:map[node]){
            if(visit[next]) continue;
            dfs(next,node);
            dp[0][node] += dp[1][next] ;
            dp[1][node] += Math.min(dp[0][next],dp[1][next]);
        }
    }
}
