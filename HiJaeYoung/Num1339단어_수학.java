package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Num1339단어_수학 {
    private static class Pair{
        int index;
        int value;
        Pair(int index,int value) {
            this.index = index;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[30];
        for(int i=0;i<n;i++){
            String input = br.readLine();
            int pow = input.length()-1;
            for(char c :input.toCharArray()){
                arr[c-'A'] += (int) Math.pow(10,pow);
                pow --;
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.value-o1.value;
            }
        });
        for(int i=0;i<11;i++){
            pq.add(new Pair(i,arr[i]));
        }
        int cnt = 9;
        int answer = 0;
        while(!pq.isEmpty()){
            Pair now = pq.poll();
            if(now.value==0) break;
            answer += now.value*cnt;
            cnt--;
        }
        System.out.println(answer);
    }
}
