package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Input
    정점의 수 N(2>=, <=800), 간선의 수 E (0 <=, <= 200,000), 간선의 비용이 양수
OutPut 1sec
    특정 노드의 간의 최단거리, 못 가면 -1 가 아니고, 특정 노드 두개를 무적권 경유하는 최단 거리를 구해라

1. 다익스트라
    - 경유해야 하는 점에 대한 최단거리를 구해서 케이스 분리
*/
public class Num1504 {
    private static class Edge{
        int target;
        int cost;
        Edge(int target,int cost){
            this.target = target;
            this.cost = cost;
        }
    }
    private static class Pair{
        int nowNode;
        int cost;
        Pair(int nowNod,int cost){
            this.nowNode = nowNod;
            this.cost =cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        List<List<Edge>> map = new ArrayList<>();
        for(int i=0;i<=n;i++){
            map.add(new ArrayList<>());
        }
        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map.get(node1).add(new Edge(node2,cost));
            map.get(node2).add(new Edge(node1,cost));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[] minDist1 = dijkstra(map,n,start);
        int[] minDist2 = dijkstra(map,n,end);

        if(minDist1[1] == Integer.MAX_VALUE || minDist1[n] == Integer.MAX_VALUE || minDist1[end] == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        int dist = 0;
        dist = minDist1[1]+minDist1[end]+minDist2[n];
        dist = Math.min(dist,minDist2[1]+minDist2[start]+minDist1[n]);
        System.out.println(dist);

    }
    public static int[] dijkstra(List<List<Edge>> map, int n, int start){
        int[] minDist = new int[n+1];
        boolean[] visit = new boolean[n+1];
        Arrays.fill(minDist,Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cost - o2.cost;
            }
        });
        minDist[start] = 0;
        pq.add(new Pair(start,0));
        int visitedNode = 0;
        while(!pq.isEmpty()){
            Pair pair= pq.poll();
            if (visit[pair.nowNode]) continue;
            visit[pair.nowNode] = true;
            visitedNode ++;
            for(Edge edge : map.get(pair.nowNode)){
                if(minDist[edge.target] > edge.cost+pair.cost){
                    minDist[edge.target] = edge.cost+pair.cost;
                    pq.add(new Pair(edge.target,minDist[edge.target]));
                }
            }
            if(visitedNode == n) break;
        }
        return minDist;
    }
}
