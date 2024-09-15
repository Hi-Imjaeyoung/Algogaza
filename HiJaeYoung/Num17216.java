package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Num17216 {
    static int n,max,min;
    static char[][] map;
    static int[] dx = {1,0}, dy = {0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n+1][n+1];
        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                map[i][j] = st.nextToken().charAt(0);
            }
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        List<Character> list = new ArrayList<>();
        list.add(map[1][1]);
        DFS(1,1,list,new boolean[n+1][n+1]);
        System.out.println(max+" "+min);
    }
    public static void DFS(int x, int y, List<Character> list, boolean[][] visited){
        if(x==n && y==n){
//            System.out.println(list);
            calculate(list);
            return;
        }
        for(int i=0;i<2;i++){
            int nxtX = x+dx[i];
            int nxtY = y+dy[i];
            if(nxtX>0 && nxtX<=n && nxtY>0 && nxtY<=n && !visited[nxtX][nxtY]){
                visited[nxtX][nxtY] = true;
                list.add(map[nxtX][nxtY]);
                DFS(nxtX,nxtY,list,visited);
                visited[nxtX][nxtY] = false;
                list.remove(list.size()-1);
            }
        }
    }
    public static void calculate(List<Character> list){
        int now = Character.getNumericValue(list.get(0));
//        System.out.println(now);
        for(int i=1;i<list.size();i++){
            if(i%2==0){
                if(list.get(i-1)=='+') now += Character.getNumericValue(list.get(i));
                if(list.get(i-1)=='-') now -= Character.getNumericValue(list.get(i));
                if(list.get(i-1)=='*') now *= Character.getNumericValue(list.get(i));
            }
        }
        max = Math.max(max,now);
        min = Math.min(min,now);
    }
}
