package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Num3980선발명단 {
    private static class Man{
        int number;
        int score;
        public Man(int number,int score){
            this.number = number;
            this.score = score;
        }
    }
    static int answer;
    static boolean stop;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(testCase -- >0){
            List<List<Man>> list = new ArrayList<>();
            answer =0;
            stop = false;
            for(int i=0;i<11;i++){
                st = new StringTokenizer(br.readLine());

                for(int j=0;j<11;j++){
                    if(list.size()<11) list.add(new ArrayList<>());
                    int score = Integer.parseInt(st.nextToken());
                    if(score == 0) continue;
                    list.get(j).add(new Man(i,score));
                }
            }
//            for(List<Man> manList:list){
//                manList.sort(new Comparator<Man>() {
//                    @Override
//                    public int compare(Man o1, Man o2) {
//                        return o2.score - o1.score;
//                    }
//                });
//            }
            boolean[] check = new  boolean[11];
            calculate(0,0,list,check);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
    public static void calculate(int now,int value,List<List<Man>> list, boolean[] check){
        if(now == 11){
            answer = Math.max(answer,value);
//            stop = true;
            return;
        }
        for(int i=0;i<list.get(now).size();i++) {
            Man man = list.get(now).get(i);
            if(check[man.number]) continue;
            check[man.number] = true;
            calculate(now+1,value+ man.score,list,check);
            check[man.number] = false;
        }
    }
}
