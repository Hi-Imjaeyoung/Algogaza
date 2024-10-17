package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Num11404플로이드 {
    static int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int edge = Integer.parseInt(br.readLine());
        int[][] ans = new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(ans[i],INF);
            ans[i][i] = 0;
        }
        StringTokenizer st;
        for(int i=0;i<edge;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            ans[s][e] = Math.min(ans[s][e],cost);

        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=n;k++){
                    if(ans[j][i] + ans[i][k] < ans[j][k]) ans[j][k] = ans[j][i] + ans[i][k];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(ans[i][j] != INF){
                    sb.append(ans[i][j]).append(" ");
                }else{
                    sb.append(0).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
