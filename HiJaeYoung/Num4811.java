package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
Input
    테스트 케이스의 수 <= 1000, 약의 개수 N<=30
Output 1sec
    가능 한 조합의 수

1. 조합
    - method(n,1,n-1,1)
        if n == 1 return 1;
        return method(n,2,n-2,2) + method(n,2,n-1,0);

 */
public class Num4811 {
    static long[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        memo = new long[31][31];
        while (true){
            int n = Integer.parseInt(br.readLine());
            if(n==0) break;
            long answer = method(n,0);
            sb.append(answer).append("\n");

        }
        System.out.print(sb);
    }
    public static long method(int w, int h){
        if(w == 0) return 1;
        if(memo[w][h]!=0) return memo[w][h];
        if(h>0){
            memo[w][h] = method(w-1,h+1) + method(w,h-1);
            return memo[w][h];
        }
        memo[w][h] = method(w-1,h+1);
        return memo[w][h];
    }
}
