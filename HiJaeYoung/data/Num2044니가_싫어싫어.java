package HiJaeYoung.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num2044니가_싫어싫어 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        int maxSize = 0;
//        int s = 0;
//        int e = 0;
//        HashMap<Integer,Integer> hashMap = new HashMap<>();
//        for(int i=0;i<n;i++){
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            int nowS = Integer.parseInt(st.nextToken());
//            int nowE = Integer.parseInt(st.nextToken());
//            hashMap.put(nowS,hashMap.getOrDefault(nowS,0)+1);
//            hashMap.put(nowE,hashMap.getOrDefault(nowE,0)-1);
//        };
//        List<Integer> keys = new ArrayList<>(hashMap.keySet());
//        keys.sort(new Comparator<Integer>(){
//            @Override
//            public int compare(Integer o1,Integer o2){
//                return o1-o2;
//            }
//        });
//        int nowMos = 0;
//        boolean isChange = false;
//        for(int i : keys){
//            nowMos += hashMap.get(i);
//            if(nowMos > maxSize){
//                maxSize = nowMos;
//                s = i;
//                isChange = true;
//            }else if(nowMos<maxSize && isChange){
//                isChange = false;
//                e = i;
//            }
//        }
//        System.out.println(maxSize);
//        System.out.println(s + " "+e);
//    }
private static class Pair{
    int s;
    int e;
    Pair(int s,int e){
        this.s = s;
        this.e = e;
    }
}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Pair> list = new ArrayList<>();
        int maxSize = 0;
        int s = 0;
        int e = 0;
        boolean checker = false;
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nowS = Integer.parseInt(st.nextToken());
            int nowE = Integer.parseInt(st.nextToken());
            list.add(new Pair(nowS,nowE));
        }
        list.sort(new Comparator<Pair>(){
            @Override
            public int compare(Pair o1, Pair o2){
                if(o1.s == o2.s) return o2.e - o1.e;
                return o1.s - o2.s;
            }
        });
        for(int i=0;i<n;i++){
            Pair now = list.get(i);
            int nowS = now.s;
            int nowE = now.e;
            if(pq.isEmpty()){
                pq.add(nowE);
                if(maxSize == 0){
                    s = nowS;
                    e = nowE;
                    maxSize = pq.size();
                }
                continue;
            }else if(pq.peek() < nowS){
                while (!pq.isEmpty()&&pq.peek() < nowS){
                    if(checker){
                        e = pq.peek();
                        checker = false;
                    }
                    pq.poll();
                }
                pq.add(nowE);
            }else if (pq.peek() > nowS){
                pq.add(nowE);
            }else{
                pq.poll();
                pq.add(nowE);
            }
            if(maxSize < pq.size()){
                s = nowS;
                e = pq.peek();
                maxSize = pq.size();
                checker = true;
            }
            if(maxSize > pq.size() && checker){
                e = pq.peek();
                checker = false;
            }
        }
        if(checker){
            e = pq.peek();
        }
        System.out.println(maxSize);
        System.out.println(s + " "+e);
    }
}
