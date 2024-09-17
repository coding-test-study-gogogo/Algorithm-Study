package week11.almondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 기타리스트 {
    public static int N,S,M;
    public static int[] arr;
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N+1][M+1];
        dp[0][S] = true;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i-1][j]) {
                    int temp = j+arr[i];
                    int tempX = j- arr[i];

                    if (temp<=M) dp[i][temp] = true;
                    if (tempX>= 0) dp[i][tempX] = true;
                }
            }
        }
        int answer = -1;
        for (int i = M; i>= 0; i--) {
            if (dp[N][i]) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}