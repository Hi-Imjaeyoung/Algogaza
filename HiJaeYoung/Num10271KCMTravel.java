package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num10271KCMTravel {
    private static class Pair {
        int now;
        int cost;
        int time;
        Pair(int now, int cost , int time){
            this.now = now;
            this.cost = cost;
            this.time = time;
        }
    }
    static int n,budget, edge;
    static List<List<Pair>> edges;
    static int[][] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        budget = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        edges = new LinkedList<>();
        for(int i=0;i<=n;i++){
            edges.add(new LinkedList<>());
        }
        for(int i=0;i<edge;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges.get(start).add(new Pair(end,cost,time));
        }
        ans = new int[n+1][10001];
        for(int i=1;i<=n;i++){
            Arrays.fill(ans[i],Integer.MAX_VALUE);
            edges.get(i).sort(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o1.time - o2.time;
                }
            });
        }
        ans[1][0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.time == o2.time) return o1.cost - o2.cost;
                return o1.time - o2.time;
            }
        });
//        Deque<Pair> pq = new LinkedList<>();
        pq.add(new Pair(1,0,0));
        int min = Integer.MAX_VALUE;
        Loop1:
        while(!pq.isEmpty()){
            Pair now = pq.poll();
            if(ans[now.now][now.cost] < now.time) continue;
            for(Pair edge : edges.get(now.now)){
                if(now.cost + edge.cost <= budget){
                    if(ans[edge.now][now.cost + edge.cost] > now.time+edge.time){
                        ans[edge.now][now.cost + edge.cost] = now.time+edge.time;
                        for(int i = now.cost+edge.cost+1; i<=budget;i++){
                            if(ans[edge.now][i] > now.time+edge.time){
                                ans[edge.now][i] = now.time+edge.time;
                            }else{
                                break;
                            }
                        }
                        if(edge.now == n) min = Math.min(min, now.time+edge.time);
                        pq.add(new Pair(edge.now, now.cost+edge.cost,edge.time+ now.time));
                    }
                }
            }
        }
        if(min == Integer.MAX_VALUE){
            System.out.println("Poor KCM");
        }else{
            System.out.println(min);
        }
    }
}
