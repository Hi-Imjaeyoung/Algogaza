package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* Input
* testCase T, 연산의 수 K <= 1,000,000, 큐에 삽입 I (2^31 이하), 꺼내기 D (1 최대, -1 최소)
*
* Output 1sec
* 큐에 남아있는 최대, 최소 값 / 비어있는 경우 empty
*
* 1. stack 사용
*   - min que 에서 n개를 poll
*   - max que의 사이즈가 n개 이면 max que 초기화
*   - maxque, minque가 빈 que 이면 초기화
*   - Empty 검증을 먼저해야함.
* 2. map 사용
*   - 전체 요소의 수를 관리
* */
public class Num7662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for(int i=0;i<testCase;i++){
            int n = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minQue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(o1>o2) return 1;
                    if(o1.equals(o2)) return 0;
                    return -1;
                }
            });
            HashMap<Integer,Integer> map = new HashMap<>();
            PriorityQueue<Integer> maxQue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(o1>o2) return -1;
                    if(o1.equals(o2)) return 0;
                    return 1;
                }
            });
            StringTokenizer st;
            loop1 :
            for(int j=0;j<n;j++){
                st = new StringTokenizer(br.readLine());
                String input1 = st.nextToken();
                int input2 = Integer.parseInt(st.nextToken());
                if(input1.equals("I")){
                    maxQue.add(input2);
                    minQue.add(input2);
                    map.put(input2,map.getOrDefault(input2,0)+1);
                }else if(input2 == 1){
                    // 빈 큐에 작업 시
                    if(maxQue.isEmpty()) {
                        continue;
                    }
                    // 이미 뺀 숫자를 또 빼려고 할때
                    while (!map.containsKey(maxQue.peek())){
                        maxQue.poll();
                        if(maxQue.isEmpty()) continue loop1;
                    }
                    if(map.get(maxQue.peek())==1){
                        // 하나있는 요소를 제거
                        map.remove(maxQue.peek());
                    }else {
                        // 하나 이상 있는 요소를 제거
                        map.put(maxQue.peek(), map.get(maxQue.peek()) - 1);
                    }
                    maxQue.poll();
                }else{
                    if(minQue.isEmpty()) {
                        continue;
                    }
                    // 이미 뺀 숫자를 또 빼려고 할때
                    while (!map.containsKey(minQue.peek())){
                        minQue.poll();
                        if(minQue.isEmpty()) continue loop1 ;
                    }
                    if(map.get(minQue.peek())==1){
                        // 하나있는 요소를 제거
                        map.remove(minQue.peek());
                    }else {
                        // 하나 이상 있는 요소를 제거
                        map.put(minQue.peek(), map.get(minQue.peek()) - 1);
                    }
                    minQue.poll();
                }
                if(map.isEmpty()){
                    maxQue.clear();
                    minQue.clear();
                }
            }
            // 남아있는 요소 정리
            while (!map.containsKey(maxQue.peek())){
                maxQue.poll();
                if(maxQue.isEmpty()) break;
            }
            while (!map.containsKey(minQue.peek())){
                minQue.poll();
                if(minQue.isEmpty()) break;;
            }
            if(maxQue.isEmpty() || minQue.isEmpty()) {
                sb.append("EMPTY").append("\n");
                continue;
            }
            sb.append(maxQue.poll()).append(" ").append(minQue.poll()).append("\n");
        }
        System.out.print(sb);
    }
}
