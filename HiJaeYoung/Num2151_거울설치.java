package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Input
    N(2<= 50>=) # 문, . 빈, !거울 가능
Output 2sec
    양쪽 문에서 서로 보이는 최소 거울의 수

1. BFS
    - 거울에는 두가지 타입이 존재
        1) /
        2) \
    - 탐색을 진행
    1) 시작점에서 보이는 거울을 확인
    2) 큐에 넣음
        - 탐색이 들어온 방향
        - 탐색 시작점
    3) 그 부분에서 또다시 보이는 거울을 확인
    4) 다시 큐에 넣음
        - 큐를 2개 쓸지..아님 큐 사이즈로 계산할지
    - 탐색을 더 빠르게 진행하기 위해 입력 단계에서 각 지점들에 대한 관계정리.
        - 문이 들어온 경우 List<List<Pair>> 에 추가.
        - 거울이 들어온 경우 List<List<Pair>[]> 에 추가.
            1. 문 리스트를 순회 하여 문에서 보이는 거울인지 확인 보이면 문 리스트에 넣어줌.
            2. 거울 리스트
                - 0 번쨰 : N S 방향에서 볼때
                - 1 번쨰 : E W 에서 볼 때
            => 벽이 늘 테두리에 존재한다는 보장이 없음.
     - 문에서 문이 바로 보이는 경우
     - 문에서 여러개의 거율을 볼 수 있을 때, 첫 거울이 아닌 다음 거울이 정답인 경우.
        5
        *****
        *!..#
        *!.!*
        *.!.*
        *#***
     - 거울을 패스 할 수 있음.
     - 거울을 패스하면.. pq를 써야함.ㅠ
     - 한번 지나온 거울을 어떻게 체크할까..
        백트래킹으로 변경..ㅠ 2^50*50인데
     - 방문 체크를 방문했을때 거울 수로 하여 재방문 막기
*/
public class Num2151_거울설치 {
    private static class Pair{
        int x;
        int y;
        int direction;
        int number;

        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        Pair(int x, int y,int direction,int number){
            this.direction = direction;
            this.x = x;
            this.y = y;
            this.number = number;
        }
    }
    static List<Pair> door;
    static List<Pair> mirror;
    static char[][] map;
    static int n;
    static int[] dx = {-1,0,1,0}, dy={0,1,0,-1};
    static int[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        door = new ArrayList<>();
        mirror = new ArrayList<>();
        map = new char[n][n];
        visit = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(visit[i],Integer.MAX_VALUE);
            String input = br.readLine();
            for(int j=0;j<n;j++){
                map[i][j] = input.charAt(j);
                if(input.charAt(j) == '!'){
                    mirror.add(new Pair(i,j));
                }
                if(input.charAt(j) == '#'){
                    door.add(new Pair(i,j));
                }
            }
        }
        PriorityQueue<Pair> que = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.number - o2.number;
            }
        });
        // 첫 문에서 보이는 거울을 탐색
        for(int i=0;i<4;i++){
            int  cnt = 1;
            while (checkMap(door.get(0),i,cnt)){
                if(map[door.get(0).x+dx[i]*cnt][door.get(0).y+dy[i]*cnt] == '!'){
                    visit[door.get(0).x+dx[i]*cnt][door.get(0).y+dy[i]*cnt] = 1;
                    que.add(new Pair(door.get(0).x+dx[i]*cnt,door.get(0).y+dy[i]*cnt,i,1));
                }
                if(map[door.get(0).x+dx[i]*cnt][door.get(0).y+dy[i]*cnt] == '#'){
                    System.out.println(0);
                    return;
                }
                cnt++;
            }
        }
//        System.out.println(que.size());
        int answer = 0;
        Loop1 :
        while(true){
            int queSize = que.size();
            answer++;
            Loop2 :
            for(int i=0;i<queSize;i++){
                Pair now = que.poll();
                if(now.direction %2 ==0){
                    int cnt = 1;
                    while (checkMap(now,1,cnt)){
                        if(map[now.x+dx[1]*cnt][now.y+dy[1]*cnt] == '!') {
                            if(visit[now.x+dx[1]*cnt][now.y+dy[1]*cnt] <= now.number) break;
                            visit[now.x+dx[1]*cnt][now.y+dy[1]*cnt] = now.number+1;
                            que.add(new Pair(now.x + dx[1] * cnt, now.y + dy[1] * cnt, 1,now.number+1));
                        }else if(map[now.x+dx[1]*cnt][now.y+dy[1]*cnt] == '#'){
                            break Loop1;
                        }
                        cnt++;
                    }
                    cnt = 1;
                    while (checkMap(now,3,cnt)){
                        if(map[now.x+dx[3]*cnt][now.y+dy[3]*cnt] == '!') {
                            if(visit[now.x+dx[3]*cnt][now.y+dy[3]*cnt] <= now.number) break;
                            visit[now.x+dx[3]*cnt][now.y+dy[3]*cnt] = now.number+1;
                            que.add(new Pair(now.x + dx[3] * cnt, now.y + dy[3] * cnt, 3, now.number+1));
                        }else if(map[now.x+dx[3]*cnt][now.y+dy[3]*cnt] == '#'){
                            break Loop1;
                        }
                        cnt++;
                    }
                }else{
                    int cnt = 1;
                    while (checkMap(now,0,cnt)){
                        if(map[now.x+dx[0]*cnt][now.y+dy[0]*cnt] == '!') {
                            if(visit[now.x+dx[0]*cnt][now.y+dy[0]*cnt] <= now.number) break;
                            visit[now.x+dx[0]*cnt][now.y+dy[0]*cnt] = now.number+1;
                            que.add(new Pair(now.x + dx[0] * cnt, now.y + dy[0] * cnt, 0, now.number+1));
                        }else if(map[now.x+dx[0]*cnt][now.y+dy[0]*cnt] == '#'){
                            break Loop1;
                        }
                        cnt++;
                    }
                    cnt = 1;
                    while (checkMap(now,2,cnt)){
                        if(map[now.x+dx[2]*cnt][now.y+dy[2]*cnt] == '!') {
                            if(visit[now.x+dx[2]*cnt][now.y+dy[2]*cnt] <= now.number) break;
                            visit[now.x+dx[2]*cnt][now.y+dy[2]*cnt] = now.number+1;
                            que.add(new Pair(now.x + dx[2] * cnt, now.y + dy[2] * cnt, 2,now.number+1));
                        }else if(map[now.x+dx[2]*cnt][now.y+dy[2]*cnt] == '#'){
                            break Loop1;
                        }
                        cnt++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
    public static boolean checkMap(Pair pair, int index, int cnt){
        if(pair.x+dx[index]*cnt<n && pair.x+dx[index]*cnt>=0 &&
                pair.y+dy[index]*cnt<n && pair.y+dy[index]*cnt>=0 &&
                map[pair.x+dx[index]*cnt][pair.y+dy[index]*cnt] != '*'){
            return true;
        }
        return false;
    }
}
