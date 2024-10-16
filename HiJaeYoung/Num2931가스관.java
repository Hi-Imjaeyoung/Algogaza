package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Num2931가스관 {
    static int r,c,numberOfPipe;
    static Pipe[][] map;
    static int[] dx={0,1,0,-1,0}, dy={0,0,-1,0,1};
    static char[] types ={'-','|','+','1','2','3','4'};
    static Queue<Pair> que;
    private static class Pipe{
        char type;
        boolean[] visit;
        Pipe(char type){
            this.type = type;
            if(type=='+') visit = new boolean[2];
            if(type!='+') visit = new boolean[1];
        }
        public void comback(Pair now){
            numberOfPipe++;
            if (type == '+'){
                visit[now.direction%2] = false;
            }else{
                visit[0] = false;
            }
        }
        public Pair pass(Pair now){
            if (type == '-'){
                if(visit[0]) return null;
                if(now.direction == 4){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r, now.c+1, now.direction);
                }else if(now.direction == 2){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r, now.c-1, now.direction);
                }
            }else if(type == '|'){
                if(visit[0]) return null;
                if(now.direction == 1){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r+1,now.c, now.direction);
                }else if(now.direction == 3){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r-1,now.c, now.direction);
                }
            }else if (type == '+'){
                if(visit[now.direction%2]) return null;
                if(now.direction == 1){
                    visit[now.direction%2] = true;
                    numberOfPipe--;
                    return new Pair(now.r+1, now.c, now.direction);
                }else if(now.direction == 3){
                    visit[now.direction%2] = true;
                    numberOfPipe--;
                    return new Pair(now.r-1, now.c, now.direction);
                }else if(now.direction == 2){
                    visit[now.direction%2] = true;
                    numberOfPipe--;
                    return new Pair(now.r, now.c-1, now.direction);
                }else {
                    visit[now.direction%2] = true;
                    numberOfPipe--;
                    return new Pair(now.r, now.c+1, now.direction);
                }
            }else if (type == '1'){
                if(visit[0]) return null;
                if(now.direction == 2){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r+1,now.c,1);
                }else if(now.direction == 3) {
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r,now.c+1,4);
                }
            }else if (type == '2'){
                if(visit[0]) return null;
                if(now.direction == 1){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r,now.c+1,4);
                }else if(now.direction == 2){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r-1,now.c,3);
                }
            }else if (type == '3'){
                if(visit[0]) return null;
                if(now.direction == 1){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r,now.c-1,2);
                }else if(now.direction ==4 ){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r-1,now.c,3);
                }
            }else{
                if(visit[0]) return null;
                if(now.direction == 4){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r+1,now.c,1);
                }else if(now.direction == 3){
                    this.visit[0] = true;
                    numberOfPipe--;
                    return new Pair(now.r,now.c-1,2);
                }
            }
            return null;
        }
    }
    private static class Pair{
        int r,c,direction;
        Pair(int r, int c, int direction){
            this.r = r;
            this.c = c;
            this.direction = direction;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        Pair start = null;
        Pair end = null;
        map = new Pipe[r][c];
        // 입력 때 누락된 파이프 포함하게 1 시작
        numberOfPipe = 1;
        for(int i=0;i<r;i++){
            String input = br.readLine();
            for(int j=0;j<c;j++){
                if(input.charAt(j) == '.') continue;
                if(input.charAt(j) == 'M'){
                    start = new Pair(i,j,0);
                }else if(input.charAt(j) == 'Z'){
                    end = new Pair(i,j,0);
                }else if(input.charAt(j) == '-'){
                    numberOfPipe++;
                    map[i][j] = new Pipe('-');
                }else if(input.charAt(j) == '|'){
                    numberOfPipe++;
                    map[i][j] = new Pipe('|');
                }else if(input.charAt(j) == '+'){
                    numberOfPipe+=2;
                    map[i][j] = new Pipe('+');
                }else if(input.charAt(j) == '1'){
                    numberOfPipe++;
                    map[i][j] = new Pipe('1');
                }else if(input.charAt(j) == '2'){
                    numberOfPipe++;
                    map[i][j] = new Pipe('2');
                }else if(input.charAt(j) == '3'){
                    numberOfPipe++;
                    map[i][j] = new Pipe('3');
                }else {
                    numberOfPipe++;
                    map[i][j] = new Pipe('4');
                }
            }
        }
        que = new LinkedList<>();
        for(int i=1;i<5;i++){
            if(start.r+dx[i] <r
                && start.r+dx[i]>=0
                && start.c+dy[i]<c
                && start.c+dy[i]>=0
                && map[start.r+dx[i]][start.c+dy[i]] != null) {
                que.add(new Pair(start.r+dx[i], start.c+dy[i],i));
                break;
            }
        }
        Pair blank = null;
        while (!que.isEmpty()) {
            Pair now = que.poll();
//            System.out.println(now.r + " "+ now.c+" "+now.direction);
            if (map[now.r][now.c] == null) {
                sb.append(now.r + 1).append(" ").append(now.c + 1).append(" ");
                blank = new Pair(now.r, now.c, now.direction);
                break;
            }
            Pipe nowPipe = map[now.r][now.c];
            Pair next = nowPipe.pass(now);
            que.add(next);
        }

        for(int i =0 ;i<types.length;i++){
            map[blank.r][blank.c] = new Pipe(types[i]);
//            System.out.println(numberOfPipe);
            if(DFS(blank,end)){
                sb.append(types[i]);
                break;
            }
        }
        System.out.println(sb);
    }
    public static boolean DFS(Pair now,Pair end){
        if(now.r>=r || now.r < 0 || now.c >= c || now.c <0) return false;
        // 모든 파이프에 가스가 흘러야 한다.
        if(now.r == end.r && now.c == end.c && numberOfPipe <= 0){
            return true;
        }
        Pipe nowPipe = map[now.r][now.c];
        if (nowPipe == null) return false;
        Pair next = nowPipe.pass(now);
        if(next == null){
            return false;
        }
        boolean result = DFS(next,end);
        nowPipe.comback(now);
        return result;
    }
}
