package HiJaeYoung.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num1943동전_분배 {
    static int sum1,sum2;
    static boolean answer;
    private static class Pair{
        int value;
        int cnt;
        Pair(int value,int cnt){
            this.value = value;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
//            @Override
//            public int compare(Integer o1, Integer o2){
//              return o2-o1;
//            };
//        });
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<3;i++){
            int n = Integer.parseInt(br.readLine());
            int totalSum = 0;
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<n;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                totalSum += (value*cnt);
                while(cnt>0){
                    cnt--;
                    list.add(value);
                }

            }
            sum1 = 0;
            answer = false;
            boolean[] dp = new boolean[100_001];
            find(list,dp,totalSum,0);
            if(answer){
                sb.append(1).append("\n");
            }else{
                sb.append(0).append("\n");
            }
        }
        System.out.print(sb);
    }
    public static void find(List<Integer> list,boolean[] dp,int totalSum,int i){
        if(answer) return;
        if(sum1 == totalSum){
            answer = true;
            return;
        }
        if(i == list.size()){
            dp[sum1] = true;
            return;
        }
        int value = list.get(i);
        if(sum1 < totalSum){
            if(sum1 + value <= totalSum && !dp[sum1+value]){
                dp[sum1+value] = true;
                totalSum -= value;
                sum1 += value;
                find(list,dp,totalSum,i+1);
                sum1 -= value;
                totalSum += value;
            }
            find(list,dp,totalSum,i+1);
        }
    }
}
