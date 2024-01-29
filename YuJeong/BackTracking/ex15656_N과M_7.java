import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ex15656_N과M_7 {
    static StringBuilder sb = new StringBuilder();
    static int[] num, combi;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        num = new int[n];
        combi = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        DFS(0);
        System.out.println(sb);
    }

    static void DFS(int L){
        if(L==m){
            for(int number : combi){
                sb.append(number).append(" ");
            }
            sb.append("\n");
        }
        else{
            for (int i = 0; i < n; i++) {
                combi[L] = num[i];
                DFS(L+1);
            }
        }
    }
}
