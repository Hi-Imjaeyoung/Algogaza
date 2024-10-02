package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 문제 조건이 부족한 것 같아
// 1. 노드 정점의 String 값이 달라도 구조가 같으면 같은 트리인지?
//   아니라고 가정.
public class Num4993_뉴턴의사과 {
    static int testCase;
    static boolean isSame;
    static Stack<String> stack1,stack2;
    static HashMap<String, List<String>> map1,map2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        for(int i=0;i<testCase;i++){
            stack1 = new Stack<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (true){
                String input = st.nextToken();
                if(input.equals("end")) break;
                stack1.add(input);
                if(input.equals("nil")) continue;
                map1.put(input, new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine());
            stack2 = new Stack<>();
            while (true){
                String input = st.nextToken();
                if(input.equals("end")) break;
                stack2.add(input);
                if(input.equals("nil")) continue;
                map2.put(input, new ArrayList<>());
            }
            // 빈 트리
            if(stack1.isEmpty() && stack2.isEmpty()){
                sb.append(true).append("\n");
                continue;
            }else if(stack1.isEmpty() || stack2.isEmpty()){
                sb.append(false).append("\n");
                continue;
            }
            String root1 = stack1.pop();
            findSubTree(root1,map1,stack1);
            String root2 = stack2.pop();
            findSubTree(root2,map2,stack2);

            isSame = compareTree(root1,root2);
            sb.append(isSame).append("\n");
        }
        System.out.print(sb);
    }
    public static void findSubTree(String now,HashMap<String,List<String>> map,Stack<String> stack){
        for(int i=0;i<2;i++){
            if(stack.isEmpty()) return;
            String tmp = stack.pop();
            if(tmp.equals("nil")){
                continue;
            }
            map.get(now).add(tmp);
            findSubTree(tmp,map,stack);
        }
    }
    public static boolean compareTree(String root1, String root2){
        if(!root1.equals(root2)) return false;
        if(map1.get(root1).size() == map2.get(root2).size()){
            List<String> list1 = map1.get(root1);
            List<String> list2 = map2.get(root2);
            Collections.sort(list1);
            Collections.sort(list2);
            for(int i=0;i<list1.size();i++){
                if(!compareTree(list1.get(i),list2.get(i))) return false;
            }
        }else{
            return false;
        }
        return true;
    }
}
