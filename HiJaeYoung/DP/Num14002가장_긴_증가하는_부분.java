package HiJaeYoung.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Num14002가장_긴_증가하는_부분 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxLen = 1;
        int maxIndex = 0;
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            for(int j = 0; j<i;j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    if(maxLen < dp[i]){
                        maxIndex = i;
                        maxLen = dp[i];
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxLen).append("\n");
        Stack<Integer> stack = new Stack<>();
        for(int i=maxIndex;i>=0;i--){
            if(dp[i] == maxLen){
                stack.add(arr[i]);
                maxLen --;
            }
        }
        while (!stack.isEmpty()){
            sb.append(stack.pop()+" ");
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(sb);
    }
}
