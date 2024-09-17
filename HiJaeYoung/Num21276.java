package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

/*
Input
    사람 수: N 10^3, 사람들 이름: name (영어 소문자, 중복x, 1<= len <=6 )
    , 기억하는 사람 수 : M (N*(N-1)/2)
OutPut 1Sec
    가문의 개수
    각 가문의 시조들의 이름(공백으로 구분, 사전 순)
    사람의 이름, 자식 수, 자식들 이름(공백으로 구분, 사전 순)

1.Union find
    - union 배열에 값이 인덱스와 동일하다면 최고 조상이다.
    - class 만들기
        static int : 가문 계열의 수
        List<?> : 자식노드
        ? : 부모 노드
        String name : 현재 노드의 이름
        int numericValue : 현재 노드의 번호이름
    - String을 효율적으로 searching 할 수 있게 Hashmap을 통해 numericValue를 부여
 */
public class Num21276 {
    private static class Node{
        static int numberOfTree;
        int level;
        List<Integer> sonNode;
        List<Integer> parentNode;
        String name;
        int numericValue;

        public Node(String name,int numericValue){
            numberOfTree++;
            sonNode = new ArrayList<>();
            this.name = name;
            this.numericValue = numericValue;
            this.parentNode = new ArrayList<>();
            this.level = 0;
        }

        public void addSon(int sonNumber){
            sonNode.add(sonNumber);
        }

        public void addParent(int parentNumber){
            if(this.parentNode.isEmpty()) numberOfTree--;
            this.level++;
            this.parentNode.add(parentNumber);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        HashMap<String,Integer> map = new HashMap<>();
        Node[] nodes = new Node[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            String name = st.nextToken();
            nodes[i] = new Node(name,i);
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        for(int i=0;i<n;i++){
            map.put(nodes[i].name,i);
        }
        int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int son = map.get(st.nextToken());
            int parent = map.get(st.nextToken());
            nodes[son].addParent(parent);
            nodes[parent].addSon(son);
        }
//        System.out.println(Node.numberOfTree);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb.append(Node.numberOfTree).append("\n");
        for(int i=0;i<n;i++){
            if(nodes[i].level==0){
                sb.append(nodes[i].name).append(" ");
            }
            Collections.sort(nodes[i].sonNode);
            sb2.append(nodes[i].name).append(" ");
            StringBuilder sb3 = new StringBuilder();
            int size = 0;
            for(int son:nodes[i].sonNode){
                if(nodes[son].level-nodes[i].level ==1) {
                    size++;
                    sb3.append(nodes[son].name).append(" ");
                }
            }
            sb2.append(size).append(" ").append(sb3);
            sb2.append("\n");
        }
        sb.append('\n');
        System.out.print(sb);
        System.out.print(sb2);
    }
}
