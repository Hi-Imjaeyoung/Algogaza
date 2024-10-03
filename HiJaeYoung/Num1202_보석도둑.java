package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num1202_보석도둑 {
    private static class Jurlly{
        int w;
        int p;
        Jurlly(int w,int p){
            this.w = w;
            this.p = p;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Jurlly> pq1 = new PriorityQueue<>(new Comparator<Jurlly>(){
            @Override
            public int compare(Jurlly o1, Jurlly o2) {
                return o1.w - o2.w;
            }
        });
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            pq1.add(new Jurlly(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        List<Integer> list = new LinkedList<>();
        for(int i=0;i<k;i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        PriorityQueue<Jurlly> pq2 = new PriorityQueue<>(new Comparator<Jurlly>(){
            @Override
            public int compare(Jurlly o1, Jurlly o2) {
                if(o1.p == o2.p) return o1.w - o2.w;
                return o2.p - o1.p;
            }
        });
        Long answer =0L;
        for(int nowSize : list){
            while(true){
                if(pq1.isEmpty() || pq1.peek().w > nowSize ) break;
                pq2.add(pq1.poll());
            }
            if(pq2.isEmpty()) continue;
            answer += pq2.poll().p;
        }
        System.out.println(answer);
    }
}
