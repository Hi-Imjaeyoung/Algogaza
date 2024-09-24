package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Input
    교차로의 수 N, 골목의 수 M, 교차로 번호 A, 도착 교차료 번호 B, 가진 돈 C
Output 5sec
    수치심이 최소
    C 이하로 가는 경로 중 골목요금의 최댓값과 최솟값을 출력, 못가면 -1

1. 구현
    - Pair[]
        수치심, 돈
    - walker
        전 노드 번호, 사용한 금액, 현재 노드
    - 현재 경로의 요금이 C를 초과 시 break
    - 현재 경로의 요금이 작은 경우
        1. 현재 경로의 최대수치심이 방문노드에 저장된 수치심 보다 크다면 break
        2. 작다면 update 후 진행
    - 같은 경우
        1. 수치심 비교. 같다면 pass
        2. 수치심이 더 작다면 update

*/
public class Num20183 {
    private static class Edge{
        int node;
        long cost;
        Edge(int node, long cost){
            this.cost = cost;
            this.node = node;
        }
    }
    private static class Pair{
        long shame;
        long cost;
        Pair(long shame, long cost){
            this.cost = cost;
            this.shame = shame;
        }

        @Override
        public String toString() {
            return shame +" "+cost;
        }
    }
    private static class Walker{
        int now;
        long shame;
        long cost;
        Walker(int now,long shame ,long cost){
            this.cost = cost;
            this.now = now;
            this.shame = shame;
        }
    }
    static int n,m,start,end;
    static long money;
    static List<List<Edge>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        money = Long.parseLong(st.nextToken());
        map = new ArrayList<>();
        for(int i=0;i<=n;i++){
            map.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            st =  new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            long point3 = Long.parseLong(st.nextToken());
            map.get(point1).add(new Edge(point2,point3));
            map.get(point2).add(new Edge(point1,point3));
        }
        System.out.println(search());
    }
    public static long search(){
        Pair[] pairs = new Pair[n+1];
        pairs[start] = new Pair(0,0);
        PriorityQueue<Walker> que = new PriorityQueue<>(new Comparator<Walker>() {
            @Override
            public int compare(Walker o1, Walker o2) {
                if(o1.shame==o2.shame){
                    if(o1.cost == o2.cost) return 0;
                    if(o1.cost > o2.cost) return 1;
                    return -1;
                }
                if(o1.shame > o2.shame) return 1;
                return -1;
            };
        });
        que.add(new Walker(start,0,0));
        while (!que.isEmpty()){
            Walker now = que.poll();
            if(now.now == end) break;
            for(Edge edge : map.get(now.now)){
                if(edge.cost+now.cost > money) continue;
                if(edge.cost+now.cost <= money){
                    if(pairs[edge.node] == null){
                        pairs[edge.node] = new Pair(Math.max(edge.cost, now.shame),edge.cost+now.cost);
                        que.add(new Walker(edge.node,Math.max(edge.cost, now.shame),edge.cost+now.cost));
                        continue;
                    }
                    if(pairs[edge.node].shame > Math.max(edge.cost, now.shame)){
                        pairs[edge.node].shame = Math.max(edge.cost, now.shame);
                        pairs[edge.node].cost = now.cost+edge.cost;
                        que.add(new Walker(edge.node,pairs[edge.node].shame,now.cost+edge.cost));
                        continue;
                    }
                    if(pairs[edge.node].shame == Math.max(edge.cost, now.shame)){
                        if(pairs[edge.node].cost > now.cost+edge.cost){
                            pairs[edge.node].cost = now.cost+edge.cost;
                            que.add(new Walker(edge.node,pairs[edge.node].shame, now.cost+edge.cost));
                        }
                    }

                }
            }
        }
        if(pairs[end] == null){
            return -1;
        }
//        System.out.println(Arrays.toString(pairs));
        return pairs[end].shame;
    }
}
