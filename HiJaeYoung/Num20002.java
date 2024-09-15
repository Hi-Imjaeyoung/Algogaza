package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Num20002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = map[0][0];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                answer = Math.max(answer,calculate(n,i,j,map));
            }
        }
        System.out.println(answer);

    }
    public static int calculate(int n,int i,int j,int[][] map){
        int maxValue = map[i][j];
        int value = map[i][j];
        int k =1;
        while(true){
            if(i+k<n && j+k<n){
                for(int l=i;l<=i+k;l++){
                    value += map[l][j+k];
                }
                for(int l=j;l<=j+k;l++){
                    value += map[i+k][l];
                }
                value -= map[i+k][j+k];
                maxValue = Math.max(value,maxValue);
                k += 1;
            }else{
                break;
            }
        }
        return maxValue;
    }
}
