package HiJaeYoung.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Input
    도시의 수 N(2<= 100,000), 도로의 수 N-1
Output 2sec
    최소 경찰서의 수

1. 구현, BFS
    - 모든 도시들 사이는 1개의 경로만 존재.
    - 연결 도로가 많은 순의 도시에 경찰서 건설해 나아간다. = 가장 많은 도로를 가진 도시부터
      경찰서를 건설하는 것은 최소 경찰서의 수를 구할 수 있다 ? (그리디?)
        - 아니네.. 도로가 많아도 이미 방문한 도시인 경우에는 최소 경찰서를 만족하지 못함
            1. 계속 초기화..?
                방문된 노드에 대해 연결된 edge 수를 감소 시켜서 다시 넣어줌.
                시간 복잡도
                    nLogn + n*(nLogn + n)
            2. 다른 알고리즘
    - 간선의 수로 정렬 + 경찰서 체크
2.DP
    - 현재노드에 대해, 2가지 경우가 존재한다
        1. 현재노드에 경찰서가 있는경우
        2. 자식노드에 경찰서가 있는경우
            dp[0][n] = dp[1][n자식], dp[0][n자식]
            dp[1][n] = dp[0][n자식]
 */
public class Num12978_스크루지민호2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        };
        for(int i=0;i<n-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            list.get(point1).add(point2);
            list.get(point2).add(point1);
        }
        int[][] dp = new int[2][n+1];
        method1(list,dp,new boolean[n+1],1);
        System.out.println(Math.min(dp[0][1],dp[1][1]));
    }
    public static void method1(List<List<Integer>> list,int[][] dp,boolean[] visit,int now){
        visit[now] = true;
        dp[0][now] = 1;

        for(int nxt:list.get(now)){
//            System.out.println(nxt);
            if(visit[nxt]) continue;
            method1(list,dp,visit,nxt);

            dp[0][now] += Math.min(dp[1][nxt],dp[0][nxt]);
            dp[1][now] += dp[0][nxt];
        }
    }
}
