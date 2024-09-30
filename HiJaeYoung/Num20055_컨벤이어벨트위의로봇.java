package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Input
길이가 N인 컨베이어 벨트, K개의 포인트가 0이되면 종료

Output
종료되는 시점에서의 단계를 출력

1. n Pointer
    - startPoint
    - endPoint
    - robotPoints
        - 놓은 순서대로 이동 => Que
        - 좌표
 */
public class Num20055_컨벤이어벨트위의로봇 {
    static int n,k,startPoint,endPoint,zeroBelt;
    static int[] belt;
    static Deque<Integer> que;
    static boolean[] isRobot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        belt = new int[2*n];
        isRobot = new boolean[2*n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*n;i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 1;
        startPoint = 0;
        endPoint = n-1;
        zeroBelt = 0;
        que = new LinkedList<>();
        while(true){
            rolling();
            if(robotMoving())break;
            if(addRobot())break;
            answer++;
        }
        System.out.println(answer);
    }
    public static void rolling(){
        startPoint --;
        endPoint --;
        if(startPoint <0){
            startPoint += 2*n;
        }
        if(endPoint <0){
            endPoint += 2*n;
        }
    }
    public static boolean robotMoving(){
        int size = que.size();
        for(int i=0;i<size;i++){
            int tmp = que.pollFirst();
            if(tmp == endPoint) {
                isRobot[tmp] = false;
                continue;
            }
            if(tmp+1>= 2*n) {
                if(belt[tmp+1-2*n]==0 || isRobot[tmp+1-2*n]){
                    que.addLast(tmp);
                    continue;
                }
                if(tmp+1-2*n!=endPoint) {
                    isRobot[tmp+1-2*n] = true;
                    que.addLast(tmp+1-2*n);
                }
                isRobot[tmp] = false;
                belt[tmp+1-2*n] --;
                if(belt[tmp+1-2*n] == 0) zeroBelt++;
                if(zeroBelt==k) return true;
            }else{
                if(belt[tmp+1]==0 || isRobot[tmp+1]){
                    que.addLast(tmp);
                    continue;
                }
                if(tmp+1 != endPoint){
                    isRobot[tmp+1] = true;
                    que.addLast(tmp+1);
                }
                isRobot[tmp] = false;
                belt[tmp+1] --;
                if(belt[tmp+1] == 0) zeroBelt++;
                if(zeroBelt==k) return true;
            }
        }
        return false;
    }
    public static boolean addRobot(){
        if(belt[startPoint] >0 && !isRobot[startPoint]){
            belt[startPoint] --;
            if(belt[startPoint] == 0) zeroBelt++;
            isRobot[startPoint] = true;
            que.add(startPoint);
        }
        return zeroBelt == k;
    }
}
