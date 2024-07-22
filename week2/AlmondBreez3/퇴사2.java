package week2.AlmondBreez3;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 퇴사2 {
    public static int N;
    public static int[] dp;
    public static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        arr = new int[N][2];

        for (int i =0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());
            arr[i][0] = temp1;
            arr[i][1] = temp2;
        }

        for (int i = 0; i < N; i++) {
            if (arr[i][0] + i <= N) {
                dp[i+arr[i][0]] = Math.max(dp[i+arr[i][0]],dp[i]+arr[i][1]);
            }
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }
        System.out.println(dp[N]);
    }
}