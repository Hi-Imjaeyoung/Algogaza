package HiJaeYoung.Graph.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num1719택배 {
    private static class Pair{
        int pre;
        int now;
        int cost;
        Pair( int now, int cost){
            this.cost = cost;
            this.now = now;
        }
        Pair(int pre,int now, int cost){
            this.pre = pre;
            this.now = now;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        List<List<Pair>> list = new ArrayList<>();
        int tmp = n;
        while (tmp -->= 0){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(p1).add(new Pair(p2,cost));
            list.get(p2).add(new Pair(p1,cost));
        }
        int[][] ans = new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o1.cost - o2.cost;
                }
            });
            int[] dis = new int[n+1];
            Arrays.fill(dis,Integer.MAX_VALUE);
            int visitCounter = 0;
            int[] memo = new int[n+1];
            memo[i] = i;
            dis[i] = 0;
            visitCounter++;
            for(Pair pairs:list.get(i)){
                pq.add(new Pair(i,pairs.now,pairs.cost));
            }
            while (!pq.isEmpty() && visitCounter <n){
                Pair now = pq.poll();
                if(dis[now.now] > now.cost) {
                    dis[now.now] = now.cost;
                    if (now.pre == i) {
                        memo[now.now] = now.now;
                    } else {
                        memo[now.now] = memo[now.pre];
                    }
                    ans[i][now.now] = memo[now.now];
                    for(Pair nxt : list.get(now.now)){
                        pq.add(new Pair(now.now,nxt.now, nxt.cost+dis[now.now]));
                    }
                    visitCounter++;
                }
            }
//            System.out.println(Arrays.toString(dis));

        }
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i == j) sb.append("-").append(" ");
                if(i != j) sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
