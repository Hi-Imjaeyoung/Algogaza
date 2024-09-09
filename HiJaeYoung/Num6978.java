package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Num6978 {
    static boolean isCorrect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int[][] matches = new int[15][2];
        int count= 0;
        for(int i=0;i<6;i++){
            for(int j=i+1;j<6;j++){
                matches[count][0]= i;
                matches[count][1]= j;
                count++;
            }
        }
        loop1:
        for(int i=0;i<4;i++){
//            System.out.println("--------");
            StringTokenizer st = new StringTokenizer(br.readLine());
            isCorrect = false;
            int[][] matchResult = new int [6][3];
            int sumWin= 0;
            int sumLose= 0;
            int sumDraw= 0;
            loop2:
            for(int j=1;j<=18;j++){
                int number = Integer.parseInt(st.nextToken());
                if(j%3==1){
                    sumWin+= number;
                    matchResult[j/3][0]= number;
                }
                if(j%3==2){
                    sumDraw+= number;
                    matchResult[j/3][1]= number;
                }
                if(j%3==0){
                    sumLose+= number;
                    matchResult[(j/3)-1][2]= number;
                }
            }
            if(sumWin != sumLose){
                sb.append("0 ");
                continue;
            }
            loop3:
            for(int j=0;j<6;j++){
//                System.out.println(Arrays.toString(matchResult[j]));
                if(matchResult[j][0]+matchResult[j][1]+matchResult[j][2] !=5){
                    sb.append("0 ");
                    continue loop1;
                }
            }
            method1(matches,matchResult,0);
            if(isCorrect){
                sb.append("1 ");
                continue;
            }
            sb.append("0 ");
        }
        System.out.print(sb);
    }
    public static void method1(int[][] matches,int[][] matchResult, int nowMatchesNumber){
        if(isCorrect) return;
        if(nowMatchesNumber ==14){
            isCorrect = true;
            return;
        }
        int team1 = matches[nowMatchesNumber][0];
        int team2 = matches[nowMatchesNumber][1];

        if(matchResult[team1][0]>0 && matchResult[team2][2]>0){
            matchResult[team1][0]--;
            matchResult[team2][2]--;
            method1(matches,matchResult,nowMatchesNumber+1);
            matchResult[team1][0]++;
            matchResult[team2][2]++;
        }
        if(matchResult[team1][1]>0 && matchResult[team2][1]>0){
            matchResult[team1][1]--;
            matchResult[team2][1]--;
            method1(matches,matchResult,nowMatchesNumber+1);
            matchResult[team1][1]++;
            matchResult[team2][1]++;
        }
        if(matchResult[team1][2]>0 && matchResult[team2][0]>0){
            matchResult[team1][2]--;
            matchResult[team2][0]--;
            method1(matches,matchResult,nowMatchesNumber+1);
            matchResult[team1][2]++;
            matchResult[team2][0]++;
        }
    }
}
