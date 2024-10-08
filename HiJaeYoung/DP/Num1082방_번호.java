package HiJaeYoung.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Num1082방_번호 {
    private static class Pair{
        int num;
        int cost;
        Pair(int num,int cost){
            this.num = num;
            this.cost = cost;
        }
    }
    static int[] arr;
    static int n, totalCost;
    static String[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr= new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        totalCost = Integer.parseInt(br.readLine());
        dp = new String[51];
        Arrays.fill(dp,"");
        for(int i=0;i<n;i++){
            int cost = Integer.parseInt(st.nextToken());
            arr[i] = cost;
//            dp[cost] = String.valueOf(i);
        }
        find(totalCost,n-1);
        System.out.println(dp[totalCost]);
    }
    public static String find(int cost,int start){
        if(cost <= 0 ) return "";
        if(!dp[cost].isEmpty()) return dp[cost];
        for(int i = start;i>=0;i--){
            if(cost - arr[i] >= 0 ){
                if(dp[cost].isEmpty() && i==0){
                    dp[cost] ="0";
                }else{
                    dp[cost] = compare(dp[cost],find(cost-arr[i],start),String.valueOf(i));
                }
                dp[cost] = compare(dp[cost],find(cost,start-1),"");
            }
//            System.out.println(Arrays.toString(dp));
        }
        return dp[cost];
    }
    public static String compare(String o1, String o2, String o3) {
        StringBuilder sb = new StringBuilder();
        if (o2.length() == o3.length()) {
            if (o2.charAt(0) > o3.charAt(0)) {
                sb.append(o2).append(o3);
            } else {
                sb.append(o3).append(o2);
            }
        } else if (o3.isEmpty()){
            sb.append(o2);
        }else{
            for(int i=0;i<o2.length();i++){
                if(o2.charAt(i) <= o3.charAt(0)){
                    sb.append(o2, 0, i).append(o3).append(o2.substring(i));
                    break;
                }
            }
        }
        String str = sb.toString();
        if(str.length()>1){
            while(str.charAt(0) == '0'){
                str = str.replaceFirst("0","");
                if(str.isEmpty()) return o1;
            }
        }
        if(o2.isEmpty()) str = o3;
//        System.out.println(o1+" "+str);
        if(str.isEmpty()) return o1;
        if(o1.isEmpty()) return str;
        if(o1.length() > str.length()){
            return o1;
        }else if(o1.length() < str.length()){
            return str;
        }
        for(int i=0;i<o1.length();i++){
            if(o1.charAt(i) > str.charAt(i)) return o1;
            if(o1.charAt(i) < str.charAt(i)) return str;
        }
        return o1;
    }
}
