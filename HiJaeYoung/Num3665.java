package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
testcase : t <=100, teamNumber: n<=500, lastRanking[], changeNumber m<=2500, changedTeam1, changedTeam2
1 sec
    1. Brute force
        Find every ranking combination O(N!)
        Check change state O(m*n)
    2.
    다른 조건에 관계없이 상대등수를 변경하는 조건은 a,b가 붙어 있는 경우이다.
    즉 붙어 있는 조건이 나올때 까지 조건 자체를 순회
    Impossible 의 경우
        - 현재 조건이 성립되지 않고, 남은 조건이 없는 경우
        - 현재 조건이 성립되지 않고, 남은 조건들이 다 성립되지 않는 조건인 경우
    두 개의 que
        - 붙어있는 조건을 가지는 que
        - 붙어있지 않은 조건을 가지는 que
    탐색 순서
        1. 붙어있는 조건 que가 빌때까지 순회
        2. 붙어있지 않은 조건 que를 순회하여 이동 시킴
        3. 붙어있지 않은 조건 que의 순회 중 이동이 이뤄지지 않으면 impossible
*/
public class Num3665 {
    private static class Node{
        int number1;
        int number2;
        public Node(int number1, int number2){
            this.number1 = number1;
            this.number2 = number2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<testCase;i++){
            int n = Integer.parseInt(br.readLine());
            int[] ranking = new int[n+1];
            Queue<Node> injectQue = new LinkedList<>();
            Queue<Node> notInjectQue = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                ranking[Integer.parseInt(st.nextToken())] = j;
            }
            int m = Integer.parseInt(br.readLine());
            for(int j=0;j<m;j++){
                st = new StringTokenizer(br.readLine());
                int number1 = Integer.parseInt(st.nextToken());
                int number2 = Integer.parseInt(st.nextToken());
                if(Math.abs(ranking[number1]-ranking[number2])==1){
                    injectQue.add(new Node(number1,number2));
                    continue;
                }
                notInjectQue.add(new Node(number1,number2));
            }
            while(true){
                if(injectQue.isEmpty() && notInjectQue.isEmpty()){
                    int[] answer = new int[n];
                    for(int k=1;k<=n;k++){
                        answer[ranking[k]-1] = k;
                    }
                    String format = Arrays.toString(answer).replace("[","").replace("]","").replace(",","");
                    sb.append(format);
                    sb.append('\n');
                    break;
                }
                while(!injectQue.isEmpty()){
                    Node now = injectQue.poll();
                    if(Math.abs(ranking[now.number1]-ranking[now.number2])!=1){
                        notInjectQue.add(now);
                        continue;
                    }
                    int value = ranking[now.number1];
                    ranking[now.number1] = ranking[now.number2];
                    ranking[now.number2] = value;
                }
                int size = notInjectQue.size();
                while(size-->0){
                    Node now = notInjectQue.poll();
                    if(Math.abs(ranking[now.number1]-ranking[now.number2])==1){
                        injectQue.add(now);
                        continue;
                    }
                    notInjectQue.add(now);
                }
                if(!notInjectQue.isEmpty() && injectQue.isEmpty()){
                    sb.append("IMPOSSIBLE");
                    sb.append("\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}
