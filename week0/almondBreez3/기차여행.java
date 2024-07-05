import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int N, M;
    public static int[] rails;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //도시
        N = Integer.parseInt(st.nextToken());

        //여행하는 도시
        M = Integer.parseInt(st.nextToken());
        rails = new int[M+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            rails[i] = Integer.parseInt(st.nextToken());
        }
        int[][] money = new int[N][3];


        //조건
        for (int i =1 ;i< N;i++) {
            st = new StringTokenizer(br.readLine());
            money[i][0] = Integer.parseInt(st.nextToken());
            money[i][1] = Integer.parseInt(st.nextToken());
            money[i][2] = Integer.parseInt(st.nextToken());
        }

        // 철도 횟수 구하기
        int[] count = new int[N+1];
        for (int i = 1; i < M; i++) {
            int s = rails[i];
            int e = rails[i+1];
            if (s < e) {
                count[s]++;
                count[e]--;
            } else {
                count[e]++;
                count[s]--;
            }
        }

        int total = 0;
        int cnt = 0;
        for (int i =1; i < N; i++) {
            cnt += count[i];
            total += Math.min(money[i][0] * cnt, money[i][1] * cnt +money[i][2]);
        }

        System.out.println(total);
    }
}