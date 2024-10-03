package HiJaeYoung.Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Num30242N_Queen_Easy {
    static int n;
    static boolean[] up,line,rToL,lToR;
    static int[] answer;
    static StringBuilder sb;
    static boolean isFind;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = new int[n+1];
        up = new boolean[n+1];
        line = new boolean[n+2];;
        rToL = new boolean[2*n+1];
        lToR = new boolean[2*n+1];
        sb = new StringBuilder();
        isFind = false;
        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            answer[i] = Integer.parseInt(st.nextToken());
            if(answer[i] != 0){
                up[answer[i]] = true;
                line[i] = true;
                rToL[i+answer[i]] = true;
                lToR[checkLtoR(i,answer[i])] = true;
                count++;
            }
        }
        sb.append(-1);
        calculate(1,count);
        System.out.println(sb);
    }
    public static int checkLtoR(int x,int y){
        if(x>=y){
            return x-y;
        }
        return y-x+n;
    }
    public static void calculate(int now, int count){
        if(count == n && !isFind) {
            sb = new StringBuilder();
            for(int i:answer){
                if(i == 0) continue;
                sb.append(i).append(" ");
            }
            isFind = true;
            return;
        }
        if(isFind ||now > n) return;
        if(answer[now] != 0) calculate(now+1,count);
        for(int i=1;i<=n;i++){
            if(!up[i] && !line[now] && !rToL[now+i] && !lToR[checkLtoR(now,i)]){
                up[i] = true;
                line[now] = true;
                rToL[now+i] = true;
                lToR[checkLtoR(now,i)] = true;
                answer[now] = i;
                calculate(now+1,count+1);
                answer[now] = 0;
                up[i] = false;
                line[now] = false;
                rToL[now+i] = false;
                lToR[checkLtoR(now,i)] = false;
            }
        }
    }
}
