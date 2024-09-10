package HiJaeYoung;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Num18405 {
    static int n,k,time,x,y;
    static int[][] map;
    static int[] dx ={1,0,-1,0}, dy={0,1,0,-1};
    private static class Virus implements Comparable<Virus> {
        int x;
        int y;
        int numericValue;
        int times;

        Virus(int numericValue,int x,int y){
            this.numericValue= numericValue;
            this.x= x;
            this.y= y;
            this.times= 0;
        }
        Virus(int numericValue,int x,int y,int times){
            this.numericValue= numericValue;
            this.x= x;
            this.y= y;
            this.times= times;
        }

        @Override
        public int compareTo(Virus o){
            if(o.times==this.times){
                return this.numericValue-o.numericValue;
            }
            return this.times-o.times;
        }
    }
    public static void main(String[] args) throws IOException {
        // Input & setup
        // PriorityQue 1.times 2.virusValue
        // put Data during InputStaging
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        k= Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        PriorityQueue<Virus> pq= new PriorityQueue<>();
        for(int i=1;i<=n;i++){
            st= new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                int numericValue= Integer.parseInt(st.nextToken());
                if(numericValue!=0) pq.add(new Virus(numericValue,i,j));
                map[i][j]= numericValue;
            }
        }
        st= new StringTokenizer(br.readLine());
        int time= Integer.parseInt(st.nextToken());
        int x= Integer.parseInt(st.nextToken());
        int y= Integer.parseInt(st.nextToken());

        // condition of terminate
        // 1. times reach 6
        // 2. pq is Empty
        // 3. determine (x,y) values
        while (!pq.isEmpty()){
            Virus virus = pq.poll();
            if(virus.times==time) break;
            if(virus.x==x&&virus.y==y) break;
            for(int i=0;i<4;i++){
                int nxtX= virus.x+dx[i];
                int nxtY= virus.y+dy[i];
                if(nxtX<=n&&nxtX>0&&nxtY<=n&&nxtY>0&&map[nxtX][nxtY]==0){
                    map[nxtX][nxtY]= virus.numericValue;
                    pq.add(new Virus(virus.numericValue, nxtX,nxtY,virus.times+1));
                }
            }
        }
//        for(int i=1;i<=n;i++){
//            System.out.println(Arrays.toString(map[i]));
//        }
        System.out.println(map[x][y]);
    }
}
