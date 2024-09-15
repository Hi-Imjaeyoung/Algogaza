package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class Num13905 {
    static int n, m, startPoint, endPoint,answer;
    static List<List<Edge>> map;
    private static class Edge{
        int value;
        int cost;
        public Edge(int value,int cost){
            this.value = value;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        //Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = 0;
        st = new StringTokenizer(br.readLine());
        startPoint = Integer.parseInt(st.nextToken());
        endPoint = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();
        for(int i=0;i<=n;i++){
            map.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map.get(point1).add(new Edge(point2,cost));
            map.get(point2).add(new Edge(point1,cost));
        }
        //
        int[] maxValue = new int[n+1];
        Arrays.fill(maxValue,Integer.MAX_VALUE);
//        maxValue[startPoint] = 0;
        Queue<Integer> que = new LinkedList<>();
        que.add(startPoint);
        while(!que.isEmpty()){
//            System.out.println(Arrays.toString(maxValue));
            int nowNode = que.poll();
            for(Edge nextNode : map.get(nowNode)){
                int cost1 = Math.min(maxValue[nowNode],nextNode.cost);
                if(cost1 > maxValue[nextNode.value] || maxValue[nextNode.value] == Integer.MAX_VALUE){
                    maxValue[nextNode.value] = cost1;
                    if(nextNode.value == endPoint){
                        answer = Math.max(maxValue[endPoint],answer );
                    }
                    que.add(nextNode.value);
                }
            }
        }
        System.out.println(answer);
    }
}
