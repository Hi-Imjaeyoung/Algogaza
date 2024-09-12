package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num4803 {
    private static class Node{
        int node;
        int beforeNode;
        public Node(int node,int beforeNode){
            this.node = node;
            this.beforeNode = beforeNode;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = 1;
        StringBuilder sb = new StringBuilder();
        List<List<Integer>> map;
        while(true){
            // Input
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n==0) break;
            map = new ArrayList<>();
            for(int i = 0;i<=n;i++){
                map.add(new ArrayList<>());
            }
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int point1 = Integer.parseInt(st.nextToken());
                int point2 = Integer.parseInt(st.nextToken());
                map.get(point1).add(point2);
                map.get(point2).add(point1);
            }
            sb.append(findTree(n,testCaseNumber++,map));
            sb.append("\n");
        }
        System.out.print(sb);
    }
    public static String findTree(int n,int testCaseNumber,List<List<Integer>> map){
        int numberOfTree = 0;
        boolean[] visited = new boolean[n+1];
        Queue<Node> que = new LinkedList<>();
        for(int i=1;i<=n;i++){
            //pass checkNode
//            System.out.println(Arrays.toString(visited));
            if(visited[i]) continue;
            boolean isTree = true;
            que.add(new Node(i,0));
            visited[i] = true;
            while(!que.isEmpty()){
                Node nowNode = que.poll();
//                System.out.println(nowNode.node+" "+nowNode.beforeNode);
                for(int nextNode : map.get(nowNode.node)){
                    if(visited[nextNode] && nextNode!=nowNode.beforeNode) isTree = false;
                    if(!visited[nextNode]){
                        visited[nextNode] = true;
                        que.add(new Node(nextNode,nowNode.node));
                    }

                }
            }
            if(isTree) numberOfTree += 1;
        }
        if(numberOfTree==1) return "Case "+testCaseNumber+": There is one tree.";
        if(numberOfTree>1) return  "Case "+testCaseNumber+": A forest of "+numberOfTree+" trees.";
        return "Case "+testCaseNumber+": No trees.";
    }
}
