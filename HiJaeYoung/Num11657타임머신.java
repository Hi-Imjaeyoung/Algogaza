package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num11657타임머신 {
    private static class Pair{
        int next;
        int cost;
        int now;
        Pair(int now, int next, int cost){
            this.now = now;
            this.next = next;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int e= Integer.parseInt(st.nextToken());
        List<Pair> list = new ArrayList<>();
        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Pair(p1,p2,cost));
        }
        Long[] dist = new Long[n+1];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[1] = 0L;
        boolean[] vis = new boolean[n+1];
        vis[1] = true;
        for(int i=1;i<n;i++){
            for(int j=0;j<e;j++){
                Pair now = list.get(j);
                if(dist[now.now] != Long.MAX_VALUE && dist[now.next] > dist[now.now]+now.cost) dist[now.next] = dist[now.now]+now.cost;
            }
        }
        boolean hasNegative = false;
//        System.out.println(Arrays.toString(dist));
        for(int i=0;i<e;i++){
            Pair pair = list.get(i);
            if (dist[pair.now] != Long.MAX_VALUE && dist[pair.next] > dist[pair.now] + pair.cost) {
                hasNegative = true;
                break;
            }
        }
        if(hasNegative){
            System.out.println(-1);
            return;
        }
        for(int i=2;i<=n;i++){
            if(dist[i] == Long.MAX_VALUE){
                sb.append(-1).append("\n");
                continue;
            }
            sb.append(dist[i]).append("\n");
        }
        System.out.print(sb);
    }
}
