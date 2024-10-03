package HiJaeYoung.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Num9251LCS {
    static int[][] dp;
    static String input1, input2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input1 = br.readLine();
        input2 = br.readLine();
        dp = new int[input1.length()][input2.length()];
        for(int i=0;i<input1.length();i++){
            Arrays.fill(dp[i],-1);
        }
        for(int i=0;i<input2.length();i++){
            if(i!=0 && dp[0][i-1]==1){
                dp[0][i] = 1;
                continue;
            }
            if(input1.charAt(0) == input2.charAt(i)){
                dp[0][i] = 1;
                continue;
            }
            dp[0][i] = 0;
        }
        for(int i=0;i<input1.length();i++){
            if(i!=0 && dp[i-1][0]==1){
                dp[i][0] = 1;
                continue;
            }
            if(input2.charAt(0) == input1.charAt(i)){
                dp[i][0] = 1;
                continue;
            }
            dp[i][0] = 0;
        }
        find(input1.length()-1,input2.length()-1);
        System.out.println(dp[input1.length()-1][input2.length()-1]);
    }
    public static int find(int x,int y){
        if(dp[x][y]!=-1) return dp[x][y];
        if(input1.charAt(x) == input2.charAt(y)){
            dp[x][y] = find(x-1,y-1)+1;
        }else{
            dp[x][y] = Math.max(find(x-1,y),find(x,y-1));
        }
        return dp[x][y];
    }
}
