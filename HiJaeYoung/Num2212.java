package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 센서의 수 n, 집중국의 수 k
// 하나의 센서는 적어도 하나의 집중국과 통신
//
// 1 x 3 x x 6 7 x 9
// 5
// 1<= N <= 10,000 , 1<= k<=1000
// 메모리 초과로 Center 내 static int 삭제
// 시간 초과로 알고리즘 변경..
public class Num2212 {
    static int n, k, answer;
    static List<Integer> sensor;

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        answer = 0;
        sensor = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            sensor.add(Integer.parseInt(st.nextToken()));
        }
        // sort
        Collections.sort(sensor);
        //
        List<Integer> distance = new ArrayList<>();
        for(int i=0;i<n-1;i++){
            distance.add(sensor.get(i+1)-sensor.get(i));
        }
        Collections.sort(distance);
        //
        for(int i=0;i<n-k;i++){
            answer += distance.get(i);
        }
        System.out.println(answer);
    }
}
