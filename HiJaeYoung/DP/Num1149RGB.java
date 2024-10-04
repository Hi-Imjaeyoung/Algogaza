package HiJaeYoung.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Num1149RGB {
    static int n;
    static int[][] cost;
    static int[][] arr;
    static final int R=0, G=1, B=2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][3];
        for(int i=0;i<n;i++){
            Arrays.fill(arr[i],Integer.MAX_VALUE);
        }
        cost = new int[n][3];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            find(n-1,i);
            answer = Math.min(arr[n-1][i],answer);
        }
        System.out.println(answer);
    }
    public static int find(int now,int color){
        if(now==0) {
            return cost[0][color];
        }
        if(arr[now][color] != Integer.MAX_VALUE) return arr[now][color];
        arr[now][color] = cost[now][color];
        arr[now][color] += Math.min(find(now-1,(color+2)%3),find(now-1,(color+1)%3));
        return arr[now][color];
    };
}
