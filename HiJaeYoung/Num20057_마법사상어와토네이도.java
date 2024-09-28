package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
input
N (%2 =0 , 499), A[r][c] <= 1000, 가운데는 0

Output 1sec
격자 밖으로 나간 모래의 양 출력

1. 탐색
    - 탐색 dfs
    - 하나의 사이클 관리.
        사이클이 끝날떄 마다 +1
*/
public class Num20057_마법사상어와토네이도 {
    static int n,answer;
    static int[][] map;
    static int[] dx={-1,0,1,0} , dy={0,-1,0,1};
    private static class Location{
        int x;
        int y;
        Location(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = 0;
        int i = n/2 ,j = n/2;
        dfs(new Location(i,j),1,0,0,1);
        System.out.println(answer);
    }
    public static void  dfs(Location now, int count, int tmp,int button,int index){
        if(now.x == 0 && now.y ==0) return;
        if(count>tmp){
            int nxtX = now.x+dx[index];
            int nxtY = now.y+dy[index];
            if(map[nxtX][nxtY] != 0 ){
                 calculate(nxtX,nxtY,index);
            }
            dfs(new Location(nxtX,nxtY),count,tmp+1,button,index);
        }else{
            index++;
            index = index%4;
            if(button==1){
                int nxtX = now.x+dx[index];
                int nxtY = now.y+dy[index];
                if(map[nxtX][nxtY] != 0 ){
                     calculate(nxtX,nxtY,index);
                }
                dfs(new Location(nxtX,nxtY),count+1,1,0,index);
            }else{
                int nxtX = now.x+dx[index];
                int nxtY = now.y+dy[index];
                if(map[nxtX][nxtY] != 0 ){
                     calculate(nxtX,nxtY,index);
                }
                dfs(new Location(nxtX,nxtY),count,1,1,index);
            }

        }
    }
    public static void calculate(int nxtX,int nxtY,int index){
        int sand = map[nxtX][nxtY];
        map[nxtX][nxtY] = 0;
        int point1 = sand/100;
        int point2 = sand/10;
        int point3 = (sand*7/100);
        int point4 = (sand*2/100);
        int point5 = (sand*5/100);
        int point6 = sand -(point1*2+point2*2+point3*2+point4*2+point5);
        List<Integer> sands = new ArrayList<>();
        sands.add(point5);
        sands.add(point6);
        sands.add(point4);
        sands.add(point3);
        sands.add(point4);
        sands.add(point3);
        int sandsIndex = 0;
        if(index==0){
            if(nxtX+1 <n && nxtY+1<n){
                map[nxtX+1][nxtY+1] += point1;
            }else{
                answer += point1;
            }
            if(nxtX+1 <n && nxtY-1>=0){
                map[nxtX+1][nxtY-1] += point1;
            }else{
                answer += point1;
            }
            if(nxtX-1 >=0 && nxtY+1<n){
                map[nxtX-1][nxtY+1] += point2;
            }else{
                answer += point2;
            }
            if(nxtX-1>=0 && nxtY-1>=0){
                map[nxtX-1][nxtY-1] += point2;
            }else{
                answer += point2;
            }
        }else if(index==1){
            if(nxtX+1 <n && nxtY+1<n){
                map[nxtX+1][nxtY+1] += point1;
            }else{
                answer += point1;
            }
            if(nxtX+1 <n && nxtY-1>=0){
                map[nxtX+1][nxtY-1] += point2;
            }else{
                answer += point2;
            }
            if(nxtX-1 >=0 && nxtY+1<n){
                map[nxtX-1][nxtY+1] += point1;
            }else{
                answer += point1;
            }
            if(nxtX-1>=0 && nxtY-1>=0){
                map[nxtX-1][nxtY-1] += point2;
            }else{
                answer += point2;
            }
        }else if(index==2){
            if(nxtX+1 <n && nxtY+1<n){
                map[nxtX+1][nxtY+1] += point2;
            }else{
                answer += point2;
            }
            if(nxtX+1 <n && nxtY-1>=0){
                map[nxtX+1][nxtY-1] += point2;
            }else{
                answer += point2;
            }
            if(nxtX-1 >=0 && nxtY+1<n){
                map[nxtX-1][nxtY+1] += point1;
            }else{
                answer += point1;
            }
            if(nxtX-1>=0 && nxtY-1>=0){
                map[nxtX-1][nxtY-1] += point1;
            }else{
                answer += point1;
            }
        }else{
            if(nxtX+1 <n && nxtY+1<n){
                map[nxtX+1][nxtY+1] += point2;
            }else{
                answer += point2;
            }
            if(nxtX+1 <n && nxtY-1>=0){
                map[nxtX+1][nxtY-1] += point1;
            }else{
                answer += point1;
            }
            if(nxtX-1 >=0 && nxtY+1<n){
                map[nxtX-1][nxtY+1] += point2;
            }else{
                answer += point2;
            }
            if(nxtX-1>=0 && nxtY-1>=0){
                map[nxtX-1][nxtY-1] += point1;
            }else{
                answer += point1;
            }
        }
        for(int i=index;i<index+4;i++){
            for(int j=2;j>0;j--) {
                if (i - index == 2) continue;
                int nextX = nxtX + dx[i % 4] * j;
                int nextY = nxtY + dy[i % 4] * j;
                if (nextX < n && nextX >= 0 && nextY >= 0 && nextY < n) {
                    map[nextX][nextY] += sands.get(sandsIndex);
                } else {
                    answer += sands.get(sandsIndex);
                }
                sandsIndex++;
            }
        }
    }
}
