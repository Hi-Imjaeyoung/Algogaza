package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
소팅할때 Collections.sort 대신 Arrays.sort를 사용해야 합니다.

이유는 소팅에서 primitive type의 경우 dual pivot quicksort가 수행되는데 non primitive type은 merge sort가 수행이 되요.

참조지역성으로 인해 merge sort에서는 캐시 히트율이 떨어져 퀵소트보다 느립니다.
 */
public class Num7453합이0인네정수 {
    static int n, checkPoint;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[] arrA = new int[n];
        int[] arrB = new int[n];
        int[] arrC = new int[n];
        int[] arrD = new int[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            arrA[i] = Integer.parseInt(st.nextToken());
            arrB[i] = Integer.parseInt(st.nextToken());
            arrC[i] = Integer.parseInt(st.nextToken());
            arrD[i] = Integer.parseInt(st.nextToken());
        }
        int[] sumA = new int[n*n];
        int[] sumB = new int[n*n];
        int index = 0;
        HashMap<Integer,Long> map = new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                sumA[index]= (arrA[i]+arrB[j]);
                sumB[index] = (arrC[i]+arrD[j]);
//                map.put(sumB[index],map.getOrDefault(sumB[index],0L)+1);
                index++;
            }
        }
        Arrays.sort(sumA);
        Arrays.sort(sumB);
        Long answer = 0L;
        checkPoint = (n*n)-1;
//        System.out.println(Arrays.toString(sumA));
//        System.out.println(Arrays.toString(sumB));
        for (Integer integer : sumA) {
//            System.out.println(integer);
//            if(integer+sumB[checkPoint]<0) break;
            answer += findUpper(-integer, sumB) - findLower(-integer,sumB);
        }
        System.out.println(answer);
    }
    public static int findUpper(int value, int[] arr){
        int start = 0;
        int end = arr.length;
        while(start<end) {
            int mid = (end + start) / 2;
            if (value >= arr[mid]) {
                start = mid + 1;
            } else  {
                end = mid;
            }
        }
        return start;
    }
    public static int findLower(int value, int[] arr){
        int start = 0;
        int end = arr.length;
        while(start<end) {
            int mid = (end + start) / 2;
            if (value > arr[mid]) {
                start = mid + 1;
            } else  {
                end = mid;
            }
        }
        return start;
    }
}
