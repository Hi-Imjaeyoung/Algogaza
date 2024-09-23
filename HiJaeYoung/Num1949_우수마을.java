package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Num1949_우수마을 {
    static int n;
    static int[] people;
    static List<Integer>[] map;
    static boolean[] visit;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        people = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            map[i] = new ArrayList<>();
        }
        for(int i=1;i<=n;i++){
            people[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            map[point1].add(point2);
            map[point2].add(point1);
        }
        visit = new boolean[n+1];
        dp = new int [2][n+1];
        dfs(1);
        System.out.println(Math.max(dp[0][1],dp[1][1]));
    }
    public static void dfs(int node){
        if (visit[node]) {
            return;
        }
        visit[node] = true;
        dp[1][node] = people[node];
        for(int next : map[node]){
            if(visit[next]) continue;
            dfs(next);

            dp[1][node] += dp[0][next];
            dp[0][node] += Math.max(dp[0][next],dp[1][next]);
        }
    }
}
